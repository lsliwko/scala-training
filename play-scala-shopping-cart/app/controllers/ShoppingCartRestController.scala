package controllers

import com.google.gson.stream.{JsonReader, JsonWriter}
import com.google.gson.{GsonBuilder, JsonElement, JsonNull, JsonSerializationContext, JsonSerializer, TypeAdapter}
import models.{Item, ItemRepository, ShoppingCart, ShoppingCartRepository}
import play.api.Logger
import play.api.mvc._

import javax.inject._
import scala.collection.concurrent.TrieMap
import scala.concurrent.{ExecutionContext, Future}
import scala.jdk.CollectionConverters._

//class OptionSerializer extends JsonSerializer[Option[Any]] {
//  override def serialize(src: Option[Any], typeOfSrc: java.lang.reflect.Type, context: JsonSerializationContext): JsonElement = {
//    src match {
//      case None => JsonNull.INSTANCE
//      case Some(v) => context.serialize(v)
//    }
//  }
//
//}

class OptionTypeAdapter extends TypeAdapter[Option[Any]] {

  private val gson = new GsonBuilder().create()

  override def write(out: JsonWriter, value: Option[Any]): Unit =
    value match {
      case o: Option[_] => o match {
        case Some(v) => gson.toJson(v, v.getClass, out)
        case None =>
          // we must forcibly write null in order the read method to be called
          val orig = out.getSerializeNulls
          out.setSerializeNulls(true)
          out.nullValue()
          out.setSerializeNulls(orig)
      }
    }

  override def read(in: JsonReader): Option[Any] = Option(gson.fromJson(in, classOf[Long]))

}

@Singleton
class ShoppingCartRestController @Inject()(
                                            val controllerComponents: ControllerComponents,
                                            val itemRepository: ItemRepository,
                                            val shoppingCartRepository: ShoppingCartRepository
                                          )(implicit executionContext: ExecutionContext) extends BaseController {

  private val logger = Logger(getClass)

  //  case class ShoppingCart(id: Long, itemIDs: List[Long] = List.empty)
  //  private val shoppingCarts = TrieMap[Long, ShoppingCart]()

  private val gson = new GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(classOf[Option[Any]], new OptionTypeAdapter)
    .create


  //TODO create shopping cart object and return id
  def newEmpty(): Action[AnyContent] = Action.async { _ =>

    shoppingCartRepository.insert(ShoppingCart(None, "")).map {
      case None =>
        BadRequest("Id does not exist.")
      case Some(shoppingCartId) =>
        logger.info(s"Created new shopping cart.")
        Ok(s"${shoppingCartId}")
    }
  }

  //TODO remove all items from shopping cart
  def itemsEmpty(shoppingCartId: Long): Action[AnyContent] = Action.async { _ =>
    shoppingCartRepository.findById(shoppingCartId).map {
      case None =>
        BadRequest(s"${shoppingCartId} not found.")

      case Some(shoppingCart) =>
        val newShoppingCart = shoppingCart.copy(itemsJson = "")
        shoppingCartRepository.update(shoppingCartId, newShoppingCart)
        Ok(newShoppingCart.itemsJson)
    }
  }

  //TODO show all items in shopping cart
  def itemsAll(shoppingCartId: Long): Action[AnyContent] = Action.async { _ =>
    shoppingCartRepository.findById(shoppingCartId).map {
      case None =>
        BadRequest(s"${shoppingCartId} not found.")

      case Some(shoppingCart) =>
        Ok(shoppingCart.itemsJson)
    }
  }

  //TODO add item and return new shopping cart
  def itemsAdd(shoppingCartId: Long, itemId: Long): Action[AnyContent] = Action.async { _ =>
    val newShoppingCartFuture = for {
      itemOption <- itemRepository.findById(itemId) //start in parallel
      shoppingCartOption <- shoppingCartRepository.findById(shoppingCartId) //start in parallel
    } yield {
      (itemOption, shoppingCartOption) match {  //we can make tuple and
        case (_, None) => Left(s"Shopping Cart ${shoppingCartId} not found.")
        case (None, _) => Left(s"Item ${itemId} not found.")
        case (Some(item), Some(shoppingCart)) =>
          val items = gson.fromJson(shoppingCart.itemsJson, classOf[Array[Item]]) //deserializing from JSON to object
          val newShoppingCart = shoppingCart.copy(itemsJson = gson.toJson(items :+ item)) //serializing from object to JSON
          shoppingCartRepository.update(shoppingCartId, newShoppingCart)
          Right(newShoppingCart.itemsJson)
      }
    }

    newShoppingCartFuture.map {
      case Left(error) => BadRequest(error)
      case Right(result) => Ok(result)
    }
  }

  /*
  def itemsAdd(shoppingCartId: Long, itemId: Long): Action[AnyContent] = Action.async { _ =>
    shoppingCartRepository.findById(shoppingCartId).map {
      case None => Future.successful { Left(s"Shopping Cart ${shoppingCartId} not found.") }
      case Some(shoppingCart) => {
        itemRepository.findById(itemId).map {
          case None => Left(s"Item ${itemId} not found.")
          case Some(item) =>
            val items = gson.fromJson(shoppingCart.itemsJson, classOf[Array[Item]]) //deserializing from JSON to object
            val newShoppingCart = shoppingCart.copy(itemsJson = gson.toJson(items :+ item)) //serializing from object to JSON
            shoppingCartRepository.update(shoppingCartId, newShoppingCart)
            Right(newShoppingCart.itemsJson)
        }
      }
    }.flatten.map {
      case Left(error) => BadRequest(error)
      case Right(result) => Ok(result)
    }
  }
  */
}