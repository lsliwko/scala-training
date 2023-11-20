package com.sparktest.kafka

import com.sparktest.domain.UserData
import com.sparktest.function.UserFunctions
import com.sparktest.kafka.serializer.{TestJsonDeserializer, TestJsonSerializer}
import io.github.embeddedkafka.EmbeddedKafka
import org.apache.kafka.common.serialization.{Deserializer, Serializer}
import org.apache.spark.sql.functions.{col, udf}
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.scalatest.flatspec._
import org.scalatest.matchers.should.Matchers._

import java.nio.charset.StandardCharsets

class UserFunctionsKafkaStreamingTest extends AnyFlatSpec with SparkKafkaStreamingTester {

  val topic = "test_topic"
  val tableName = "test_table"
  val testMessage = "test_message"
  
  import sparkSession.implicits._

  implicit val serializer: Serializer[UserData] = new TestJsonSerializer[UserData]

  val decoderFunction: Array[Byte] => UserData = (v: Array[Byte]) => {
    val deserializer: Deserializer[UserData] = new TestJsonDeserializer[UserData]
    deserializer.deserialize("", v)
  }
  
  val testUserDatas: Seq[UserData] = List(
    UserData("Les", "lsliwko@gmail.com", java.sql.Timestamp.valueOf("2000-12-01 00:00:00")),
    UserData("Tom", "tom@facebook.com", java.sql.Timestamp.valueOf("2010-01-01 00:00:00")),
    UserData("Louis", "louis@gmail.com", java.sql.Timestamp.valueOf("2015-06-15 00:00:00"))
  )

  "spark" should "receive message from Kafka" in {
    val usersDatastream = sparkSession.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", s"localhost:${kafkaConfig.kafkaPort}")
      .option("subscribe", topic)
      .option("startingOffsets", "earliest")
      .load()

    val query = usersDatastream.writeStream
      .format("memory")
      .queryName(tableName)
      .outputMode(OutputMode.Append())
      .trigger(Trigger.Once())
      .start()

    EmbeddedKafka.publishStringMessageToKafka(topic, testMessage)
    query.processAllAvailable()

    val results = sparkSession.sql(f"SELECT value FROM $tableName").collect()
    results.length should be(1)
    val messageBytes = results.head.getAs[Array[Byte]]("value")
    new String(messageBytes, StandardCharsets.UTF_8) should be(testMessage)
  }

  "UserFunctions" should "filter users by email hostname" in {
    val usersDatastream = sparkSession.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", s"localhost:${kafkaConfig.kafkaPort}")
      .option("subscribe", topic)
      .option("startingOffsets", "earliest")
      .load()

    val decoderUdf = udf(decoderFunction)

    val udfDataset = usersDatastream.withColumn("decoded", decoderUdf(col("value")))
      .select("decoded.*")
      .as[UserData]

    val userFunctions = new UserFunctions(sparkSession)
    val resultDataset = userFunctions.filterByEmailHost(udfDataset, "gmail.com".r)

    val query = resultDataset.writeStream
      .format("memory")
      .queryName(tableName)
      .outputMode(OutputMode.Append())
      .trigger(Trigger.Once())
      .start()

    for (testUserData <- testUserDatas) {
      EmbeddedKafka.publishToKafka(topic, testUserData)
    }
    query.processAllAvailable()

    val results = sparkSession.sql(f"SELECT * FROM $tableName").as[UserData].collect()
    results.length should be(2)

    results(0) should be (UserData("Les", "lsliwko@gmail.com", java.sql.Timestamp.valueOf("2000-12-01 00:00:00")))
    results(1) should be (UserData("Louis", "louis@gmail.com", java.sql.Timestamp.valueOf("2015-06-15 00:00:00")))
  }

  "UserFunctions" should "obfuscate users' emails" in {
    val usersDatastream = sparkSession.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", s"localhost:${kafkaConfig.kafkaPort}")
      .option("subscribe", topic)
      .option("startingOffsets", "earliest")
      .load()

    val decoderUdf = udf(decoderFunction)

    val udfDataset = usersDatastream.withColumn("decoded", decoderUdf(col("value")))
      .select("decoded.*")
      .as[UserData]

    val userFunctions = new UserFunctions(sparkSession)
    val resultDataset = userFunctions.obfuscateEmails(udfDataset)

    val query = resultDataset.writeStream
      .format("memory")
      .queryName(tableName)
      .outputMode(OutputMode.Append())
      .trigger(Trigger.Once())
      .start()

    for (testUserData <- testUserDatas) {
      EmbeddedKafka.publishToKafka(topic, testUserData)
    }
    query.processAllAvailable()

    val results = sparkSession.sql(f"SELECT * FROM $tableName").as[UserData].collect()
    results.length should be(3)

    results(0) should be(UserData("Les", "lxxxxxx@gmail.com", java.sql.Timestamp.valueOf("2000-12-01 00:00:00")))
    results(1) should be(UserData("Tom", "txx@facebook.com", java.sql.Timestamp.valueOf("2010-01-01 00:00:00")))
    results(2) should be(UserData("Louis", "lxxxx@gmail.com", java.sql.Timestamp.valueOf("2015-06-15 00:00:00")))
  }


}
