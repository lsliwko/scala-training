package test

import scala.util.boundary
import scala.util.boundary.break

object MainAppAddStrings extends App {


  println(
    addStrings(num1 = "1", num2 = "9")
  )

  def addStrings(num1: String, num2: String): String = {

    val length = Math.max(num1.length, num2.length) + 1

    val digits0 = Array.fill(length)(0)
    var digits1 = num1.iterator.map(char => Integer.valueOf(char - 48)).toArray
    var digits2 = num2.iterator.map(char => Integer.valueOf(char - 48)).toArray

    if (digits1.length < length) digits1 = (Array.fill(length - digits1.length)(Integer.valueOf(0)) ++ digits1).toArray
    if (digits2.length < length) digits2 = (Array.fill(length - digits2.length)(Integer.valueOf(0)) ++ digits2).toArray

    for (i <- (0 until length).reverse) {
      if (i > 0) {
        digits0(i) = digits0(i) + digits1(i) + digits2(i)
        if (digits0(i) > 9) {
          digits0(i - 1) = 1
          digits0(i) = digits0(i) - 10
        }
      } else {
        digits0(i) = digits0(i) + digits1(i) + digits2(i)
      }
    }
    val result = digits0.dropWhile(_==0).mkString
    if result.isEmpty then "0" else result
  }
}