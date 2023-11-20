package lessons

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object MainFuturesChainApp extends App {

  def futA: Future[String] = Future {
    println("A start")
    Thread.sleep(5000)
    println("A finished")
    "A"
  }

  def futB: Future[String] = Future {
    println("B start")
    Thread.sleep(1000)
    println("B finished")
    "B"
  }

  def futC: Future[String] = Future {
    println("C start")
    Thread.sleep(3000)
    println("C finished")
    "C"
  }

  val futResult = for {
    valA <- futA
    valB <- futB
    valC <- futC
  } yield {
    println("futResult start")
    Thread.sleep(2000)
    println("futResult finished")
    s"$valA $valB $valC"
  }

  println("I am running now...")

  val result = Await.result(futResult, 20.seconds)

  println("The result is " + result)
}
