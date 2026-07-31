package test

object MainAppCountCharacters extends App {

  println(
    countCharacters(
      words = Array("hello","world","leetcode"),
      chars = "welldonehoneyr"
    )
  )


  def countCharacters(words: Array[String], chars: String): Int = {
    val charsMap = wordToMap(chars)
    words.map { word =>
      val wordMap = wordToMap(word)

      val unmatchedCharOption = wordMap.find { case (char, count) =>
        count > charsMap.getOrElse(char, 0)
      }

      if (unmatchedCharOption.nonEmpty) 0
      else word.length
    }.sum
  }


  def wordToMap(word : String) = {
    word.iterator.toSeq
      .groupBy(char => char)
      .map{ pair => (pair._1, pair._2.length) }
  }

}