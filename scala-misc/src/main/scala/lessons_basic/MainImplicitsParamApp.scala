package lessons

import java.util.Locale

object MainImplicitsParamApp extends App {

  //Scala's Contextual Parameters:
  // https://docs.scala-lang.org/tour/implicit-parameters.html


  //NOTE: Scala matches implicit value by type (i.e. not by name!)
  implicit val mysomething : Locale = Locale.GERMAN

  val messagesMap = Map(
    "de_hello-message" -> "Hallo",
    "en_hello-message" -> "Hi",
    "de_goodbye-message" -> "Wiedersehen",
    "en_goodbye-message" -> "Goodbye",
  )

  def getMessage(id: String) (implicit locale: Locale) : String = {
    val key = s"${locale.toString}_${id}"

    messagesMap.get(key) match {
      case Some(message) => message
      case None => {
        println(s"Message ${key} not found. Defaulting to English..")
        messagesMap.getOrElse(s"en_${id}", "[message missing]")
      }
    }
  }


  println {
    getMessage("hello-message")
  }

  println {
    getMessage("goodbye-message")(Locale.GERMAN)
  }

}
