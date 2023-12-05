package models

import javax.inject.Inject
import scala.util.{Failure, Success}
import anorm._
import anorm.SqlParser.{get, str}
import play.api.Logger
import play.api.db.DBApi

import scala.concurrent.Future

case class Item(
  id: Option[Long] = None,
  name: String
)

object Item {
  implicit def toParameters: ToParameterList[Item] = Macro.toParameters[Item]
}

@javax.inject.Singleton
class ItemRepository @Inject()(dbapi: DBApi)(implicit databaseExecutionContext: DatabaseExecutionContext) {

  private val logger = Logger(getClass)
  private val db = dbapi.database("default")

  private[models] val simple =
    get[Option[Long]]("item.id") ~ str("item.name") map { case id ~ name => Item(id, name)}

  def findById(id: Long): Future[Option[Item]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from item where id = $id".as(simple.singleOpt)
    }
  }

  def findByIds(ids: List[Long]): Future[List[Item]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from item where id in ($ids)".as(simple.*)
    }
  }

  def all: Future[Seq[Item]] = Future {
    db.withConnection { implicit connection =>
      // Anorm streaming
      SQL"select * from item order by name"
        .fold(Seq.empty[Item], ColumnAliaser.empty) { (items, row) =>
          row.as(simple) match {
            case Failure(parseErr) => logger.error(parseErr.getMessage); items
            case Success(Item(None, _)) => items
            case Success(item) => item +: items
          }
        }
    }
  }.flatMap {
    case Left(err :: _) => Future.failed(err)
    case Left(_) => Future(Seq.empty)
    case Right(acc) => Future.successful(acc.reverse)
  }

  def update(id: Long, item: Item) = Future {
    db.withConnection { implicit connection =>
      SQL"update item set name = {name} where id = {id}"
        .bind(item.copy(id = Some(id))).executeUpdate()
    }
  }

  def insert(item: Item): Future[Option[Long]] = Future {
    db.withConnection { implicit connection =>
      SQL"insert into item values (nextval('item_seq'), {name})"
        .bind(item).executeInsert()
    }
  }

  def delete(id: Long): Future[Int] = Future {
    db.withConnection { implicit connection =>
      SQL"delete from item where id = ${id}".executeUpdate()
    }
  }

}
