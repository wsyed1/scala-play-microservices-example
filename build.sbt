name := """favourites-service"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"
routesGenerator := InjectedRoutesGenerator
updateOptions := updateOptions.value.withCachedResolution(true)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "mysql" % "mysql-connector-java" % "5.1.12",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

