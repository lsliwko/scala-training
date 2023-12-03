package config

import com.google.inject.AbstractModule
import consumers.KafkaMessagesConsumer
import play.api.Logging

class KafkaModule extends AbstractModule with Logging {

  override def configure(): Unit = {
    logger.info("Starting KafkaModule")
    bind(classOf[KafkaMessagesConsumer]).asEagerSingleton()
  }
}
