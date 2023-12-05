package controllers

import com.google.gson.stream.{JsonReader, JsonWriter}
import com.google.gson.{GsonBuilder, JsonElement, JsonNull, JsonSerializationContext, JsonSerializer, TypeAdapter}
import consumers.KafkaMessagesConsumer
import play.api.Logger
import play.api.mvc._

import javax.inject.Inject
import javax.inject.Singleton
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters._

@Singleton
class KafkaRestController @Inject()(
  val kafkaMessagesConsumer: KafkaMessagesConsumer,
  val controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext) extends BaseController {

  private val logger = Logger("kafka")

  private val gson = new GsonBuilder()
    .setPrettyPrinting()
    .create

  def allMessages(): Action[AnyContent] = Action.async { _ =>
    Future {
      Ok(
        s"Messages: ${kafkaMessagesConsumer.messages.size}\n" +
        kafkaMessagesConsumer.messages
          .zipWithIndex
          .map { case (message, index) => s"$index: $message"}
          .mkString("\n")
      )
    }
  }

}