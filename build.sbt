organization := "com.agilogy"

name := "scalabcn-implicits-2015"

version := "1.0-SNAPSHOT"

crossScalaVersions := Seq("2.11.5")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

// https://tpolecat.github.io/2014/04/11/scalac-flags.html
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",       // yes, this is 2 args
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)