package config

import com.google.inject.AbstractModule
import consumers.KafkaMessagesConsumer
import play.api.{Logger, Logging}

class KafkaModule extends AbstractModule {

  private val logger = Logger(getClass)

  override def configure(): Unit = {
    logger.info("Starting KafkaModule")
    bind(classOf[KafkaMessagesConsumer]).asEagerSingleton()
  }
}
