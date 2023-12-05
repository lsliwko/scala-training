package models

import anorm.SqlParser.get
import anorm.{SqlStringInterpolation, ~}
import play.api.db.DBApi

import javax.inject.Inject
import scala.concurrent.Future

case class ShoppingCartEntity(id: Option[Long] = None,
                        itemIDs: List[Long] = List.empty) {
  def getItemIDsAsString: String = itemIDs.mkString(",")
  def emptyItems: ShoppingCartEntity = this.copy(itemIDs = List.empty)
}

object ShoppingCartEntity{
  def apply(id: Option[Long], itemIDsAsString: String): ShoppingCartEntity = itemIDsAsString match {
      case null => ShoppingCartEntity(id)
      case "" => ShoppingCartEntity(id)
      case items => ShoppingCartEntity(id, items.split(",").map(_.toLong).toList)
    }
}

@javax.inject.Singleton
class ShoppingCartRepository @Inject()(dbapi: DBApi)(implicit databaseExecutionContext: DatabaseExecutionContext) {

  private val db = dbapi.database("default")
  private[models] val parser = get[Option[Long]]("shoppingcart.id") ~ get[Option[String]]("shoppingcart.items") map { case id ~ items => ShoppingCartEntity(id, items.getOrElse(""))}

  def insert(): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL"insert into shoppingcart values (nextval('shoppingcart_seq'), null)".executeInsert()
    }
  }

  def findById(id: Long): Future[Option[ShoppingCartEntity]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from shoppingcart where id = $id".as(parser.singleOpt)
    }
  }

  def update(shoppingCart: ShoppingCartEntity): Future[Int] = Future {
    db.withConnection { implicit connection =>
      SQL"update shoppingcart set items = ${shoppingCart.getItemIDsAsString} where id = ${shoppingCart.id}".executeUpdate()
    }
  }

}
