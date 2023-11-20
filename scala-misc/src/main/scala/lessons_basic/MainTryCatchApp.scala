package lessons

import scala.collection.parallel.CollectionConverters._
import scala.collection.{immutable, mutable}
import scala.util.control.NonFatal

object MainTryCatchApp extends App {




  //in Scala we should be using Either[ErrorString, Value]
  //however, Java libraries work predominantly on Exceptions


  try {
    throw new RuntimeException("error A")
  } catch {
    case NonFatal(e) => //IMPORTANT! let exceptions like OutOfMemoryError to fall through
      println {
        s"My exception: ${e.getMessage}"
      }
  } finally {
    println {
      s"Always executed at the end"
    }
  }


  val either = scala.util.Try {
    //throw new RuntimeException("error A")
    "return value" //String
  }.toEither

  println {
    either
  }

}
