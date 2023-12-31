ThisBuild / version := "1.0.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "spark-scala-test"
  )

// spark dependencies
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.2"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.2"
libraryDependencies += "org.apache.spark" %% "spark-sql-kafka-0-10" % "3.3.2"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "3.3.2"

// test dependencies
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
libraryDependencies += "io.github.embeddedkafka" %% "embedded-kafka" % "3.4.0" % Test


javaOptions ++= Seq(

  // -J params will be added as VM parameters
  "-J-Xmx2048m",
  "-J-Xms2048m",

  // for Spark accessing internal sun.nio.ch.DirectBuffer
  "-J--add-opens=java.base/java.lang=ALL-UNNAMED",
  "-J--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
  "-J--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
  "-J--add-opens=java.base/java.io=ALL-UNNAMED",
  "-J--add-opens=java.base/java.net=ALL-UNNAMED",
  "-J--add-opens=java.base/java.nio=ALL-UNNAMED",
  "-J--add-opens=java.base/java.util=ALL-UNNAMED",
  "-J--add-opens=java.base/java.util.concurrent=ALL-UNNAMED",
  "-J--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED",
  "-J--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
  "-J--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
  "-J--add-opens=java.base/sun.security.action=ALL-UNNAMED",
  "-J--add-opens=java.base/sun.util.calendar=ALL-UNNAMED",
  "-J--add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED",

)