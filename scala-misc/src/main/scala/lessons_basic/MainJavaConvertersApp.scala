package lessons

import org.apache.commons.text.StringSubstitutor

import scala.collection.{immutable, mutable}
import scala.jdk.CollectionConverters._


object MainJavaConvertersApp extends App {

  val substitutesMap : immutable.Map[String, String] = Map(
    "user" -> "Les",
    "color" -> "red",
    "item" -> "house"
  )

  val javaMap : java.util.Map[String,String] = substitutesMap.asJava
  val scalaMap: mutable.Map[String, String] = javaMap.asScala

  // Build StringSubstitutor
  val stringSubstitutor = new StringSubstitutor(substitutesMap.asJava)

  println {
    stringSubstitutor.replace("${user} jumped over the ${color} ${item}.")
  }



  //type ascription
  println {
    String.format("%s %s %s %s", "A", "B", "C", "D")
  }

  val items = Seq("A", "B", "C", "D")
  println {
    //: _* is a special instance of type ascription which tells the compiler to treat
    // a single argument of a sequence type as a variable argument sequence, i.e. varargs.
    String.format("%s %s %s %s", items: _*)
  }

}
