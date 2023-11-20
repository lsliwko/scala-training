package lessons

import scala.util.Random

object WhenImplicitOps {

  implicit class When[A](a: A) {

    def when(flag: Boolean)(method: A => A) : A = if (flag) method(a) else a

    def when(function: () => Boolean)(method: A => A) : A = if (function()) method(a) else a

    def when(function: A => Boolean)(method: A => A) : A = if (function(a)) method(a) else a

  }
}

object MainImplicitsWhenApp extends App {

  import WhenImplicitOps._

  val exchangeDogsFlag = true
  val exchangeCatsFlag = true
  //val exchangeDislikeFlag = false

  //def exchangeDislikeFunction = Random.nextBoolean

  def exchangeDislikeFunction(flag : Boolean) = !flag

  val result = Option("cats dislike dogs")
    .when(exchangeDogsFlag) { stringOption => stringOption.map(_.replace("dogs","humans")) }
    .when(exchangeCatsFlag) { stringOption => stringOption.map(_.replace("cats","dogs")) }
    .when(exchangeDislikeFunction(false)) { stringOption => stringOption.map(_.replace("dislike","like")) }
    .orNull

//  val result = Option("cats dislike dogs")
//    .map { _.replace("dogs","humans") }
//    .map { _.replace("cats","dogs") }
//    .map { _.replace("dislike","like") }
//    .orNull


  println {
    result
  }
}
