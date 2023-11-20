package lessons

import scala.collection.immutable
import scala.collection.parallel.CollectionConverters._

object MainFoldApp extends App {

  //QUITE IMPORTANT STRUCTURE IN SCALA!!!
  //learn by heart :)

  val itemsWithCounts : List[(String,Int)]  = immutable.List("a" -> 1, "b" -> 3, "a" -> 1, "c" -> 1, "b" -> 2)

  //initialize with new accumulator: immutable.Map.empty[String,Int]
  //Note that accumulator is immutable!
  //Check explanation about "structural sharing":
  // https://stackoverflow.com/questions/43202711/how-is-scala-so-efficient-with-lists
  val countsMap = itemsWithCounts.foldLeft(
    immutable.Map.empty[String, Int]  //zero accumulator for initial step (usually immutable collection or other object)
  ) { case (accumulator, item) =>

    //NICE PATTERN TO SPLIT TUPLE
    val (itemKey, itemValue) = item //item is a pair (2-tuple)

    println(s"Adding pair (${itemKey} -> ${itemValue}) to current accumulator ${accumulator}")
    val newAccumulator = accumulator.updatedWith(itemKey) {
      case None => Some(itemValue)
      case Some(count) => Some(count + itemValue)
    }
    println(s"New accumulator: ${newAccumulator} (it will be used in next step)\n")

    newAccumulator  //return new accumulator for next step
  }

  //accumulator from last step is returned as result

  println {
    s"Result:\n${countsMap}"
  }

}
