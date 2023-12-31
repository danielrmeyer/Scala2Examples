ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "Scala2Examples"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.17"
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.6"