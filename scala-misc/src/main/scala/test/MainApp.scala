package test

import scala.collection.mutable.ListBuffer

object MainApp extends App {

  println(
    distributeCandies(
      Array(1,1)
    )
  )

  def distributeCandies(candyType: Array[Int]): Int = {
    Math.min(candyType.length / 2, candyType.distinct.length)
  }

}