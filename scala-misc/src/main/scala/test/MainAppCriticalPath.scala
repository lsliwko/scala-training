package test

import scala.util.boundary
import scala.util.boundary.break

object MainAppCriticalPath extends App {


  println(
    criticalConnections(
      n = 6,
      connections = List(
        List(1,3),List(0,1),List(1,2),List(2,0),List(3,4),List(4,5),List(5,3)
      )
    )
  )

  //shiould be List(1,3)


  def criticalConnections(n: Int, connections: List[List[Int]]): List[List[Int]] = {
    connections.flatMap { connection =>
      println("Checking: " + connection)
      if (connection == List(1,3)) {
        println("!!!!")
      }

      //remove connection and check if servers can still be reached
      val connectionsWithoutThis = connections.filterNot(_ == connection)

      val criticalConnection = !traverseConnectionsCanServersMeet(
        startServerA = connection(0), endServerB = connection(1),
        connectionsWithoutThis
      )
      if (criticalConnection) Some(connection) else None
    }
  }

  def traverseConnectionsCanServersMeet(startServerA: Int, endServerB: Int, connections: List[List[Int]]): Boolean = {
    boundary {
      for (connection <- connections) {

        if (connection(0) == endServerB) break(true)
        if (connection(1) == endServerB) break(true)

        val connectionsWithoutThis = connections.filterNot(_ == connection)
        val canTraverseFlag = traverseConnectionsCanServersMeet(connection(0), endServerB, connectionsWithoutThis)
        if (canTraverseFlag) break(true)
      }

      false
    }
  }
}