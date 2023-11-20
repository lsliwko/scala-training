package lessons

import lessons.ConcurrentMapOps.ConcurrentMapOpsImpl

import java.util.concurrent.CopyOnWriteArrayList
import scala.collection.concurrent.TrieMap
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.ForkJoinTaskSupport
import scala.util.Random  //we need to import methods from object


object ConcurrentMapOps {

  //Type class - adding new behaviors to existing objects
  implicit class ConcurrentMapOpsImpl[A, B](val map: collection.concurrent.Map[A, B]) {

    @inline
    //see insertWith http://hackage.haskell.org/package/containers-0.6.0.1/docs/Data-Map-Strict.html
    def replaceWith(key: A, function: B => B): Option[B] = {

      //repeat till replace is successful
      while (true) map.get(key) match {
        case None => return None
        case Some(value) =>
          if (map.replace(key, value, function(value))) {
            return Some(value) //replace success - return value (exit)
          }
          else {
            Thread.`yield`()    //replace failure - give-up control to other thread
          }
      }
      None
    }

  }
}


object MainImplicitsDefApp extends App {


  val concurrentMap = new TrieMap[Long, collection.mutable.ArrayBuffer[Int]]()

  concurrentMap.put(0, new collection.mutable.ArrayBuffer[Int]())
  concurrentMap.put(1, new collection.mutable.ArrayBuffer[Int]())
  concurrentMap.put(2, new collection.mutable.ArrayBuffer[Int]())
  concurrentMap.put(3, new collection.mutable.ArrayBuffer[Int]())
  concurrentMap.put(4, new collection.mutable.ArrayBuffer[Int]())


  val parVector = (1 to 10000).par
  //parVector.tasksupport = new ForkJoinTaskSupport(new java.util.concurrent.ForkJoinPool(200))




  parVector.foreach { _ =>
    concurrentMap.replaceWith(
      key = Random.nextInt(5),
      function = oldValue => {
        if (oldValue.size<5) oldValue.addOne(0)
        oldValue //newValue
      }
    )
  }


  println {
    concurrentMap
    //concurrentMap.view.mapValues(_.size).toMap
  }

}
