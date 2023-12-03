name := """play-scala-kafka"""
version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.11"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

libraryDependencies += "com.google.guava" % "guava" % "32.0.0-jre"
libraryDependencies += "com.google.code.gson" % "gson" % "2.10.1"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.13.0"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.6.0"
