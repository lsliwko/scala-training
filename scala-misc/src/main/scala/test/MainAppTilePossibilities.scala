package test

object MainAppTilePossibilities extends App {

  println(
    numTilePossibilities(tiles = "AAB")
  )

  def numTilePossibilities(tiles: String): Int = {
    (1 to tiles.length).flatMap { len =>
      tiles.combinations(len).flatMap(_.permutations)
    }.toSet.size
  }


}