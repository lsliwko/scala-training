package models

import javax.inject.Inject
import scala.util.{Failure, Success}
import anorm._
import anorm.SqlParser.{get, list, long, scalar, str}
import models.{DatabaseExecutionContext, Item}
import play.api.Logger
import play.api.db.DBApi

import scala.concurrent.Future

case class ShoppingCart (
                          id:Option[Long],
                          itemsJson: String
                        )

object ShoppingCart {
  implicit def toParameters: ToParameterList[ShoppingCart] = Macro.toParameters[ShoppingCart]
}

@javax.inject.Singleton
class ShoppingCartRepository @Inject()(dbapi: DBApi)(implicit databaseExecutionContext: DatabaseExecutionContext) {

  private val logger = Logger(getClass)
  private val db = dbapi.database("default")

  private[models] val simple =
    get[Option[Long]]("shopping_cart.id") ~ str("itemsJson.name") map { case id ~ itemsJson => ShoppingCart(id, itemsJson) }

  def findById(id: Long): Future[Option[ShoppingCart]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from shopping_cart where id = $id".as(simple.singleOpt)
    }
  }

  def update(id: Long, shoppingCart: ShoppingCart) = Future {
    db.withConnection { implicit connection =>
      SQL"update shopping_cart set name = {name} where id = {id}"
        .bind(shoppingCart.copy(id = Some(id))).executeUpdate()
    }
  }

  def insert(shoppingCart: ShoppingCart): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL"insert into shopping_cart values ((select next value for shopping_cart_seq), {name})"
        .bind(shoppingCart).executeInsert()
    }
  }

  def delete(id: Long): Future[Int] = Future {
    db.withConnection { implicit connection =>
      SQL"delete from shopping_cart where id = ${id}".executeUpdate()
    }
  }

}