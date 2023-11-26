package com.kafkatest.helper

import io.github.embeddedkafka.{EmbeddedKafka, EmbeddedKafkaConfig}
import org.scalatest.{BeforeAndAfterEach, Suite}


trait EmbeddedKafkaTester extends Suite with BeforeAndAfterEach {

  implicit val kafkaConfig: EmbeddedKafkaConfig = EmbeddedKafkaConfig(
    kafkaPort = 9092,
    zooKeeperPort = 2181,
    customBrokerProperties = Map("auto.create.topics.enable" -> "true")
  )

  override def beforeEach(): Unit = {
    EmbeddedKafka.start()(kafkaConfig)
  }

  override def afterEach(): Unit = {
    EmbeddedKafka.stop()
  }

}
