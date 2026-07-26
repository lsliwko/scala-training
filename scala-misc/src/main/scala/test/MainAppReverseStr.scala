package test

object MainAppReverseStr extends App {


  println(
    reverseStr(
      s = "abcd", k = 2
    )
  )

  //Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
  //If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.


  def reverseStr(s: String, k: Int): String = {
    s.grouped(k)
      .zipWithIndex
      .map {
        case (s, index) => if (index % 2 == 0) s.reverse else s
      }
      .mkString
  }

}