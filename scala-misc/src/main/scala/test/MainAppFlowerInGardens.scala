package test

object MainAppFlowerInGardens extends App {


  val n = 3
  var paths = Array(
    Array(1,2),
    Array(2,3),Array(3,1)
  )

  println(
    gardenNoAdj(n = n, paths = paths)
  )

  def gardenNoAdj(n: Int, paths: Array[Array[Int]]): Array[Int] = {
    var flowerInGardens = Array.fill[Int](n)(0)

    for (gardenId <- 1 to n) {
      val flowerIds = getFlowersInNeighbors(gardenId = gardenId, paths = paths, flowerInGardens = flowerInGardens)

      val newFlowerId = (1 to 4).find(flowerId => !flowerIds.contains(flowerId)).getOrElse(throw new RuntimeException())

      flowerInGardens(gardenId-1) = newFlowerId
    }

    flowerInGardens
  }


  def getFlowersInNeighbors(gardenId: Int, paths: Array[Array[Int]], flowerInGardens : Array[Int]) = {
    //search paths for all neighbor gardens
    val neightborGardenIds = paths
      .filter(path => path(0) == gardenId || path(1) == gardenId)
      .flatMap(path => path.toSeq)
      .filterNot(_ == gardenId)

    neightborGardenIds.map(tmpGardenId => flowerInGardens(tmpGardenId-1)).filter(_ > 0)
  }


}