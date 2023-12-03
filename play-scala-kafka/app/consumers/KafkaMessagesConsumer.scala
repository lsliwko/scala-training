package consumers

import akka.Done
import akka.actor.CoordinatedShutdown
import com.google.inject.Inject
import org.apache.kafka.clients.consumer.KafkaConsumer
import play.api.Logging

import java.time.Duration
import java.util.Properties
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters._
import scala.util._

import javax.inject.Singleton


@Singleton
class KafkaMessagesConsumer @Inject()(coordinatedShutdown: CoordinatedShutdown) extends Logging {
  //https://dev.to/psstepniewski/plain-kafka-consumer-in-play-framework-2a4a

  val messages = new scala.collection.mutable.ListBuffer[String]()

  logger.info(s"Starting [${this.getClass}]")

  private val executionContext: ExecutionContext = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())
  private val stopConsumer: AtomicBoolean = new AtomicBoolean(false)

  private val properties = new Properties()
  properties.put("bootstrap.servers", "kafka:9093") //from bitami/kafka
  properties.put("group.id", s"kafka-group-1")  //used to load-balance messages among members of the same group
  properties.put("key.deserializer", classOf[org.apache.kafka.common.serialization.StringDeserializer])
  properties.put("value.deserializer", classOf[org.apache.kafka.common.serialization.StringDeserializer])

  val kafkaConsumer = new KafkaConsumer[String, String](properties)
  kafkaConsumer.subscribe(Set("play-scala-kafka-topic").asJava)

  //start async so module loads OK
  Future {
    while (!stopConsumer.get()) {
      kafkaConsumer.poll(Duration.ofSeconds(3)).asScala
        .foreach(record => {
          messages.addOne(record.value())
          logger.info(s"[${this.getClass}] receives record: $record")
        })
    }
    logger.info(s"[${this.getClass}] quits 'while(true)' loop.")
  }(executionContext)
    .andThen(_ => kafkaConsumer.close())(executionContext)
    .andThen {
      case Success(_) =>
        logger.info(s"[${this.getClass}] succeed.")
      case Failure(e) =>
        logger.error(s"[${this.getClass}] fails.", e)
    }(executionContext)

  coordinatedShutdown.addTask(CoordinatedShutdown.PhaseServiceStop, s"${this.getClass}-stop") { () =>
    logger.info(s"Shutdown-task[${this.getClass}-stop] starts.")
    stopConsumer.set(true)
    Future {
      Done
    }(executionContext).andThen {
      case Success(_) => logger.info(s"Shutdown-task[${this.getClass}-stop] succeed.")
      case Failure(e) => logger.error(s"Shutdown-task[${this.getClass}-stop] fails.", e)
    }(executionContext)
  }
}
