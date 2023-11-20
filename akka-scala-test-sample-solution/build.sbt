ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "akka-scala-test"
  )

libraryDependencies += "net.sf.supercsv" % "super-csv" % "2.4.0"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.8.4"

libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"
