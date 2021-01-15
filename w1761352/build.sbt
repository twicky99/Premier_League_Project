name := """premier-league-play"""
organization := "com.premierleague"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  guice,
  "junit" % "junit" % "4.13.1"
)

