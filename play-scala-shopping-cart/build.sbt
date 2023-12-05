name := """play-scala-shopping-cart"""

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.11"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

libraryDependencies += "com.google.guava" % "guava" % "32.0.0-jre"
libraryDependencies += "com.google.code.gson" % "gson" % "2.10.1"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.13.0"

libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "com.h2database" % "h2" % "1.4.200"
libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.7.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.5.4"

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"