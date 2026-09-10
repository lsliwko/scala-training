package test

import scala.collection.mutable.ListBuffer

object MainApp extends App {

  //    Given two strings s and t, return the minimum window substring of s such that every character
  //    in t (including duplicates) is included in the window.
  //    If there is no such substring, return the empty string "".
  //
  //    Example:
  //    Input: s = "ADOBECODEBANC", t = "ABC"
  //    Output: "BANC"

  println(
    shortestSubstring(s = "ADOBECODEBANC", t = "ABC")
  )

  def shortestSubstring(s: String, t: String) = {
    //var totalCounts = t.toCharArray.groupBy(identity()).map()




//      if (s.length == 1) {
//      s
//    } else {
//      var longest = ""
//      for (i <- 0 until s.length) {
//        for (j <- i + 1 until s.length) {
//          val current = s.substring(i, j)
//
//          if (current.reverse == current) {
//            //println(current)
//            if (current.length > longest.length) longest = current
//          }
//        }
//      }
//      longest
//    }
    ""
  }
}