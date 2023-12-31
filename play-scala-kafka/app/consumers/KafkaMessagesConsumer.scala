package consumers

import akka.Done
import akka.actor.CoordinatedShutdown
import com.google.inject.Inject
import org.apache.kafka.clients.consumer.KafkaConsumer
import play.api.Logger

import java.time.Duration
import java.util.Properties
import scala.concurrent.Future
import scala.jdk.CollectionConverters._
import scala.util._
import javax.inject.Singleton
import scala.util.control.NonFatal
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class KafkaMessagesConsumer @Inject()(coordinatedShutdown: CoordinatedShutdown) {
  //https://dev.to/psstepniewski/plain-kafka-consumer-in-play-framework-2a4a

  private val logger = Logger("kafka")
  val messages = new scala.collection.mutable.ListBuffer[String]()

  logger.info(s"Starting KafkaMessagesConsumer")

  private val properties = new Properties()
  //first, we connect any bootstrap server
  //bootstrap server sends us information about current topology (e.g. brokers' ip addresses)
  //then we connect to broker which assigns us partitions to read from
  properties.put("bootstrap.servers", "localhost:9094,kafka:9092") //from bitami/kafka
  properties.put("group.id", "kafka-group-1")  //used to load-balance messages among members of the same group
  properties.put("key.deserializer", classOf[org.apache.kafka.common.serialization.StringDeserializer])
  properties.put("value.deserializer", classOf[org.apache.kafka.common.serialization.StringDeserializer])

  private val kafkaConsumer = new KafkaConsumer[String, String](properties)
  kafkaConsumer.subscribe(Set("play-scala-kafka-topic").asJava)

  private val pollingThread = new Thread {
    override def run(): Unit = {

      logger.info(s"KafkaMessagesConsumer pooling thread started")
      while (!isInterrupted) {
        try {
          logger.info(s"KafkaMessagesConsumer pooling for records...")
          kafkaConsumer.poll(Duration.ofSeconds(5)).asScala
            .foreach(record => {
              val message = record.value()
              messages.addOne(message)
              logger.info(s"KafkaMessagesConsumer received record: $record")
            })
        } catch {
          case _ : java.lang.InterruptedException | _ : org.apache.kafka.common.errors.InterruptException =>
            Try { kafkaConsumer.close() }
            logger.info(s"KafkaMessagesConsumer pooling thread stopped")

          case NonFatal(e) =>
            logger.error(s"KafkaMessagesConsumer error", e)
            Thread.sleep(5000)
        }
      }
    }
  }
  pollingThread.start()


  coordinatedShutdown.addTask(
    phase = CoordinatedShutdown.PhaseServiceStop,
    taskName = "KafkaMessagesConsumer-stop"
  ) { () => Future {
    pollingThread.interrupt()
    while (pollingThread.isAlive) {}
    Done
  } }

}