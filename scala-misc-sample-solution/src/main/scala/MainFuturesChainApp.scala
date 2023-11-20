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

  //no future is started yet (because they are method, not object)

  val futResult: Future[String] = for {
    valA <- futA //start futA and wait for it to finish
                 //(we are calling function which will create future)
                 //(future is started on new thread, but current thread waits for the created future)
    valB <- futB //start futB and wait for it to finish (...)
    valC <- futC //start futC and wait for it to finish (...)
  } yield {
    //new future here
    //here all futA, futB and futC are finished
    //code for futResult : Future[String]
    println("futResult start")
    Thread.sleep(2000)
    println("futResult finished")
    s"$valA $valB $valC" //return value for futResult
  }

  println("I am running now...")

  //wait (in the current thread) for futResult to finish
  //(please note that up to four threads are used: futA, futB, futC and main thread OR futResult and main thread)
  val result = Await.result(futResult, 20.seconds)

  println("The result is " + result)
}
