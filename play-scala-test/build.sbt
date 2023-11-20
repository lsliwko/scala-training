name := """play-scala-test"""

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.11"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

libraryDependencies += "com.google.guava" % "guava" % "32.0.0-jre"
libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0"

// https://mvnrepository.com/artifact/org.scala-lang.modules/scala-parallel-collections
libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.13.0"

