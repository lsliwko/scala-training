package consumers

import akka.Done
import akka.actor.CoordinatedShutdown
import com.google.inject.Inject
import org.apache.kafka.clients.consumer.KafkaConsumer
import play.api.{Logger, Logging}

import java.time.Duration
import java.util.Properties
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters._
import scala.util._
import javax.inject.Singleton
import scala.util.control.NonFatal


@Singleton
class KafkaMessagesConsumer @Inject()(coordinatedShutdown: CoordinatedShutdown) {
  //https://dev.to/psstepniewski/plain-kafka-consumer-in-play-framework-2a4a

  private val logger = Logger(getClass)

  logger.info(s"Starting KafkaMessagesConsumer")

  val messages = new scala.collection.mutable.ListBuffer[String]()

  private val executionContext: ExecutionContext = ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())
  private val stopConsumer: AtomicBoolean = new AtomicBoolean(false)

  private val properties = new Properties()
  properties.put("bootstrap.servers", "localhost:9094,kafka:9092") //from bitami/kafka
  properties.put("group.id", s"kafka-group-1")  //used to load-balance messages among members of the same group
  properties.put("key.deserializer", classOf[org.apache.kafka.common.serialization.StringDeserializer])
  properties.put("value.deserializer", classOf[org.apache.kafka.common.serialization.StringDeserializer])

  val kafkaConsumer = new KafkaConsumer[String, String](properties)
  kafkaConsumer.subscribe(Set("play-scala-kafka-topic").asJava)

  //start async so module loads OK
  val pollingFuture = Future {
    logger.info(s"KafkaMessagesConsumer started")
    while (!stopConsumer.get()) {
      try {
        logger.info(s"KafkaMessagesConsumer pooling for records...")
        kafkaConsumer.poll(Duration.ofSeconds(3)).asScala
          .foreach(record => {
            messages.addOne(record.value())
            logger.info(s"KafkaMessagesConsumer receives record: $record")
          })
      } catch {
        case NonFatal(e) =>
          Thread.sleep(5000)
          logger.error(s"KafkaMessagesConsumer error", e)
      }
    }
    logger.info(s"KafkaMessagesConsumer quits 'while(true)' loop.")
  }(executionContext)
    .andThen(_ => kafkaConsumer.close())(executionContext)
    .andThen {
      case Success(_) =>
        logger.info(s"KafkaMessagesConsumer succeed")
      case Failure(e) =>
        logger.error(s"KafkaMessagesConsumer fails with error", e)
    }(executionContext)

  coordinatedShutdown.addTask(CoordinatedShutdown.PhaseServiceStop, s"KafkaMessagesConsumer-stop") { () =>
    logger.info(s"Shutdown-task[KafkaMessagesConsumer-stop] starts.")
    stopConsumer.set(true)
    Future {
      Done
    }(executionContext).andThen {
      case Success(_) => logger.info(s"Shutdown-task[KafkaMessagesConsumer-stop] succeed.")
      case Failure(e) => logger.error(s"Shutdown-task[KafkaMessagesConsumer-stop] fails with error", e)
    }(executionContext)
  }
}
