package test

import scala.util.boundary, boundary.break

object MainApp extends App {


  println(
    criticalConnections(
      n = 6,
      connections = List(
        List(0,1),List(1,2),List(2,0),List(1,3),List(3,4),List(4,5),List(5,3)
      )
    )
  )

  //Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
  //If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.


  def criticalConnections(n: Int, connections: List[List[Int]]): List[List[Int]] = {
    connections.flatMap { connection =>
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