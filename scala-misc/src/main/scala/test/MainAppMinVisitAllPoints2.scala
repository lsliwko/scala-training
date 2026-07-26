import java.util

object MainAppMinVisitAllPoints2 extends App {

  println(
    minTimeToVisitAllPoints(points = Array(Array(1,1),Array(3,4),Array(-1,0)) )
  )

  def minTimeToVisitAllPoints(points: Array[Array[Int]]): Int = {
    Range(0, points.length-1).map( index => {
      //println("A = " + util.Arrays.toString(points(index)))
      //println("B = " + util.Arrays.toString(points(index+1)))

      val distance = distanceBetweenPoints(
        points(index),
        points(index + 1)
      )

      //println(distance)
      distance
    }
    ).sum


  }

  def distanceBetweenPoints(pointA : Array[Int], pointB : Array[Int]) : Int = {
    Math.max(
      Math.abs(pointA(0) - pointB(0)),
      Math.abs(pointA(1) - pointB(1))
    )
  }


}