ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "scala-misc"
  )

libraryDependencies += "com.google.guava" % "guava" % "32.1.3-jre"
libraryDependencies += "com.google.code.gson" % "gson" % "2.10.1"
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.13.0"
libraryDependencies += "org.apache.commons" % "commons-text" % "1.10.0"
libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"
