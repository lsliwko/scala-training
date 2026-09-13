package test

import scala.collection.mutable.ListBuffer

object MainApp extends App {

  // Daily Temperatures
  // You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith day.
  //
  //Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on a future day. If there is no day in the future where a warmer temperature will appear for the ith day, set result[i] to 0 instead.
  //
  //Example 1:
  //Input: temperatures = [30,38,30,36,35,40,28]
  //Output: [1,4,1,2,1,0,0]
  //
  //Example 2:
  //Input: temperatures = [22,21,20]
  //Output: [0,0,0]

  println(
    dailyTemperatures(Array(30,38,30,36,35,40,28)).mkString(",")
  )

  def dailyTemperatures(temperatures : Array[Int]) : Array[Int] = {
    val temperatureWithIndex = temperatures.zipWithIndex.sortBy(pair => pair._1)


    val results = Array.fill[Int](temperatureWithIndex.length)(0)

    for (left <- temperatureWithIndex.indices) {
      val dayTempLeft = temperatureWithIndex(left)._1
      val dayIndexLeft = temperatureWithIndex(left)._2

      val foundDayRightOption = temperatureWithIndex.drop(left).find( dayRight => {
        val dayTempRight = dayRight._1
        val dayIndexRight = dayRight._2
        ((dayTempRight>dayTempLeft) && (dayIndexRight > dayIndexLeft))
      })

      if (foundDayRightOption.isDefined) {
        results(dayIndexLeft) = foundDayRightOption.get._2 - dayIndexLeft
      }
    }

    results
  }

}