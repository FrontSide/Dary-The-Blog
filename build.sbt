import com.typesafe.sbteclipse.core.EclipsePlugin

name := """dary"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

EclipsePlugin.EclipseKeys.preTasks := Seq()

libraryDependencies ++= Seq(  
  javaEbean,
  javaJdbc,  
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "commons-codec" % "commons-codec" % "1.5",
  "commons-io" % "commons-io" % "2.4"
)
