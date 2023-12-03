package controllers

import com.google.gson.stream.{JsonReader, JsonWriter}
import com.google.gson.{GsonBuilder, JsonElement, JsonNull, JsonSerializationContext, JsonSerializer, TypeAdapter}
import play.api.Logger
import play.api.mvc._

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters._

@Singleton
class KafkaRestController @Inject()(
  val controllerComponents: ControllerComponents
)(implicit executionContext: ExecutionContext) extends BaseController {

  private val logger = Logger(getClass)

  private val gson = new GsonBuilder()
    .setPrettyPrinting()
    .create

  def allMessages(): Action[AnyContent] = Action.async { _ =>
    Future {
      Ok(s"OK")
    }
  }

}