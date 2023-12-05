package lessons_advanced

import cats.data.Ior
import cats.{Eq, Monoid}
import cats.syntax.all._

import scala.collection.immutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/*
sealed trait TrafficLight
object TrafficLight {
  case object Red extends TrafficLight
  case object Yellow extends TrafficLight
  case object Green extends TrafficLight
}

implicit val trafficLightEq: Eq[TrafficLight] =
  new Eq[TrafficLight] {
    def eqv(a1: TrafficLight, a2: TrafficLight): Boolean = a1 == a2
  }
*/


object MainCatsApp extends App {

  val map1 = immutable.Map("a" -> 1, "b" -> 2, "c" -> 0)
  val map2 = immutable.Map("a" -> 2, "b" -> -1, "d" -> 4)

  //Monoid[Int](1)

  // Combine two maps by summing the values with common keys.
  println {
    "|+|: " +
    (map1 |+| map2)
  }

  //conversion to option
  "a".some


  // trait Semigroup[A] {
  //   def combine(x: A, y: A): A
  // }
  //
  // trait Monoid[B] extends Semigroup[A] {
  //   def empty: A
  // }

  //maps every X value into A and then combines them using the given Monoid[A] instance
  //Cats provides Monoid[Int]
  val stringList = List("aaa","b","cc")
  println {
    "foldMap: " +
    stringList.foldMap(str => str.length)
  }

  //MonoidK[Int](4) combine MonoidK[7] = MonoidK[7] (higher value)
  //apply function to map value into Option, then return first non-empty (Some)
  println {
    "foldMapK: " +
    stringList.foldMapK { str => str match {
        case "aaa" => None
        case "b" => str.some
        case "cc" => str.some
    } }
  }

  //apply function to map value into Option, then return all non-empty (Some) only if all were non-Empty
  println {
    "traverse (all non-empty): " +
    stringList.traverse { //str => str match {
      case str@"aaa" => str.some
      case str@"b" => str.some
      case str@"cc" => str.some
    }
  }

  println {
    "traverse (one empty): " +
    stringList.traverse { str => str match {
      case str@"aaa" => str.some
      case str@"b" => None
      case str@"cc" => str.some
    } }
  }


  val list1 = List("a","b","c")
  val list2 = List("1","2","3","4")

  println {
    "zip1: " +
    list1.zip(list2)
  }

  println {
    "zip2: " +
    list2.zip(list1)
  }

  //join two lists of different sizes
  println {
    "alignWith: " +
    list1.alignWith(list2) {
      case Ior.Both(item1, item2) => (item1 -> item2)
      case Ior.Left(item1) => (item1 -> "N/A")
      case Ior.Right(item2) => ("N/A" -> item2)
    }
  }


  val opt1 = Option("1")  //Monoid[Int](1)
  val opt2 = Option("2")  //Monoid[Int](2)
  val opt3 = None  //Monoid[Int](null)
  println {
    "tupled: " +
    (opt1, opt2, opt3).tupled
  }

}
