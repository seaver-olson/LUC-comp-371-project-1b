val scala3Version = "3.8.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "topwords",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-unchecked"
    ),

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "com.lihaoyi" %% "mainargs" % "0.7.4",
      "ch.qos.logback" % "logback-classic" % "1.5.12",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5"
    ),

    Test / testOptions += Tests.Argument("-oD"),
    Test / parallelExecution := false
  )
  .enablePlugins(JavaAppPackaging)
