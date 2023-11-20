package lessons

import scala.collection.{immutable, mutable}
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.ForkJoinTaskSupport

object MainCollectionsApp extends App {

  //CPU1    CPU2    CPU3    CPU4
  //L1      L1      L1      L1 (256KB-1MB high-end machines, latency: 1 nanosecond (1-2 CPU cycles), bandwidth: 1 TB/second)
  //L2      L2      L2      L2 (2-3MB, 4 nanoseconds (~10 clock cycles), 1 TB/second)
  //L3 ----D---A-B-----C-------(32-256MB, 10x slower than L2 (~40 clock cycles),	>400 GB/second)
  //RAM -----------------------(GBs, 2x slower than L3, 400 GB/second)

  //BOX1    BOX2    BOX3    BOX4
  //RAM     RAM     RAM     RAM (110ms-240ms)
  //NETWORK  ----D---A-B-----C-------(200ms+)


  val set = Set("1","2","1","3")
  println {
    s"Set: ${set}"
  }

  val list = List("1","2","1","3")
  println {
    s"List: ${list}"
  }

  val map = Map("1" -> 1, "2" -> 2, "1" -> 4, "3" -> 3) //keys are unique
  println {
    s"Map: ${map}"
  }


  val listM = mutable.ListBuffer[String]() //list
  listM.addOne("AAA")
  listM.addOne("BBB")
  println {
    listM
  }

  val listI = immutable.List[String]()
  val listI_1 = listI :+ "AAA"
  val listI_2 = listI_1 :+ "BBB"
  println {
    listI_2
  }
  
  val listI_100 = (1 to 100).toList
//  listI_100.par.foreach { item =>
//    println(item)
//  }

  //two parallel workers
  val listI_parallel = listI_100.par
  //listI_parallel.tasksupport = new ForkJoinTaskSupport(new java.util.concurrent.ForkJoinPool(4))
  listI_parallel.foreach { item =>
    println(s"[${Thread.currentThread().getId}] ${item}")
  }
}
