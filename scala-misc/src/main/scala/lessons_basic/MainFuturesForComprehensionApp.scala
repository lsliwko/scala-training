package lessons

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object MainFuturesForComprehensionApp extends App {


  //CPU1      CPU2       CPU3       CPU4
  //L1        L1         L1         L1 (cpu cycles? 3-5ns)
  //L2        L2         L2         L2 (5-11ns)
  //L3---X----X----------------------- (50-70ns)
  //MAIN RAM (30-90ms)

  var counter = 1 //(SAME PLACE IN MEMORY)
  Future {
    counter = counter + 1
  }
  Future {
    counter = counter + 1
  }
  Future {
    counter = counter + 1
  }
  Future {
    counter = counter + 1
  }
  Future {
    counter = counter + 1
  }






  val futA: Future[String] = Future {
    println("A start 5s")
    Thread.sleep(5000)
    println("A finished")
    "A"
  }

  val futB: Future[String] = Future {
    println("B start 1s")
    Thread.sleep(1000)
    println("B finished")
    "B"
  }

  val futC: Future[String] = Future {
    println("C start 3s")
    Thread.sleep(3000)
    println("C finished")
    "C"
  }


  val futResult : Future[String] = for {
    valA <- futA
    valB <- futB
    valC <- futC
  } yield {
    println("futResult start 2s")
    Thread.sleep(2000)
    println("futResult finished")
    s"$valA $valB $valC" // future result
  }

  println("I am running now...")

  val result = Await.result(futResult, 24.hours)

  println("The result is " + result)
}
