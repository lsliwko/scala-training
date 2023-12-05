package controllers

import com.google.gson.{JsonElement, _}
import models.{Item, ItemRepository, ShoppingCartRepository}
import play.api.Logger
import play.api.mvc._

import java.lang.reflect.Type
import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

class OptionSerializer extends JsonSerializer[Option[Any]] with JsonDeserializer[Option[Any]] {
  def serialize(src: Option[Any], typeOfSrc: java.lang.reflect.Type, context: JsonSerializationContext): JsonElement = {
    src match {
      case None => JsonNull.INSTANCE
      case Some(v) => context.serialize(v)
    }
  }

  override def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Option[Any] = {
    json match {
      case JsonNull.INSTANCE => None
      case elem => Some(elem)
    }
  }
}

case class ShoppingCart(id: Long, itemIds: Array[Long])

case class ItemQuantity(item: Item, quantity: Int)

@Singleton
class ShoppingCartRestController @Inject()(
  val controllerComponents: ControllerComponents,
  val itemRepository: ItemRepository,
  val shoppingCartRepository: ShoppingCartRepository
)(implicit executionContext: ExecutionContext) extends BaseController {

  private val logger = Logger(getClass)

  private val gson = new GsonBuilder()
    .setPrettyPrinting()
    .registerTypeAdapter(classOf[Option[Any]], new OptionSerializer)
    .create


  def newEmpty(): Action[AnyContent] = Action.async { _ =>
    val fShoppingCartId = shoppingCartRepository.insert()
    fShoppingCartId map {
      case None => BadRequest("Unable to create new shopping cart")
      case Some(shoppingCartId) =>
        logger.info(s"Created shopping cart $shoppingCartId")
        Ok(s"$shoppingCartId")
    }
  }

  def itemsEmpty(shoppingCartId: Long): Action[AnyContent] = Action.async { _ =>
    val fShoppingCart = shoppingCartRepository.findById(shoppingCartId)
    fShoppingCart flatMap {
      case None => Future.successful(BadRequest(s"No shopping cart with ID $shoppingCartId found"))
      case Some(shoppingCartEntity) =>
        val updatedShoppingCart = shoppingCartEntity.emptyItems
        shoppingCartRepository.update(updatedShoppingCart) map {
          case 0 => BadRequest {s"Unable to empty items from shopping cart with ID $shoppingCartId"} //TODO WRONG STATUS TYPE
          case _ =>
            logger.info(s"Emptied all items from shopping cart $shoppingCartId")
            println(s"emptied items from $shoppingCartId, updatedCart: $updatedShoppingCart")
            val shoppingCart = ShoppingCart(shoppingCartId, updatedShoppingCart.itemIDs.toArray)
            Ok{gson.toJson(shoppingCart)}
        }
    }
  }

  def itemsAll(shoppingCartId: Long): Action[AnyContent] = Action.async { _ =>
    shoppingCartRepository.findById(shoppingCartId) flatMap {
      case None => Future.successful(BadRequest{s"No shopping cart with ID $shoppingCartId found"})
      case Some(shoppingCart) =>
        val itemsCount = shoppingCart.itemIDs.map(x => (x,shoppingCart.itemIDs.count(y => y==x))).distinct.toMap
        val itemsInCart = itemRepository.findByIds(shoppingCart.itemIDs)
        itemsInCart map { items =>
          val itemCounts = for {
            item <- items
            id <- item.id
            count = itemsCount(id)
          } yield ItemQuantity(item, count)
          Ok(gson.toJson(itemCounts.toArray))
        }
    }
  }

  def itemsAdd(shoppingCartId: Long, itemId: Long): Action[AnyContent] = Action.async { _ =>
    val fShoppingCart = for {
      oItem <- itemRepository.findById(itemId)
      oShoppingCart <- shoppingCartRepository.findById(shoppingCartId)
    } yield {
      (oItem, oShoppingCart) match {
        case (_, None) => Left{s"No shopping cart with ID $shoppingCartId found"}
        case (None, _) => Left{s"Item with ID $itemId not found" }
        case (Some(_), Some(shoppingCart)) =>
          val updatedCart = shoppingCart.copy(itemIDs = shoppingCart.itemIDs++List(itemId))
          shoppingCartRepository.update(updatedCart)
          Right(ShoppingCart(shoppingCartId, updatedCart.itemIDs.toArray))
      }
    }
    fShoppingCart.map {
      case Left(error) => BadRequest(error)
      case Right(result) => Ok(gson.toJson(result))
    }
  }
}
