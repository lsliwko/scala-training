import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, DurationInt}

object MainFuturesForComprehensionApp extends App {

  val futA: Future[String] = Future {
    println("A start")
    Thread.sleep(5000)
    println("A finished")
    "A"
  }
  //futA is running (on a new thread)

  val futB: Future[String] = Future {
    println("B start")
    Thread.sleep(1000)
    println("B finished")
    "B"
  }
  //futB is running (on a new thread)

  val futC: Future[String] = Future {
    println("C start")
    Thread.sleep(3000)
    println("C finished")
    "C"
  }
  //futC is running (on a new thread)

  //ALL futA, futB and futC are running (up to four threads)

  //creates a new future futResult and start it on new thread
  val futResult = for {
    valA <- futA  //futA is already running and wait for it to finish (in current thread)
    valB <- futB  //futB is already running and wait for it to finish (in current thread)
    valC <- futC  //futC is already running and wait for it to finish (in current thread)
  } yield {
    println("futResult start")
    Thread.sleep(2000)
    println("futResult finished")
    s"$valA $valB $valC"
  }
  //futResult is running (on a new thread)

  println("I am running now...")

  //wait (in the current thread) for futResult to finish
  //(please note that up to five threads are used: futA, futB, futC, futResult and main thread)
  val result = Await.result(futResult, 10.seconds)

  println("The result is " + result)
}
