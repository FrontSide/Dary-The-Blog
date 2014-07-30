name := """first"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(  
  javaEbean,
  javaJdbc,  
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)

## "postgresql" % "postgresql" % "9.1-901.jdbc4"