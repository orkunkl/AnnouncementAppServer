name := """AnnouncementServer"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
  "org.postgresql" % "postgresql" % "9.4.1212",
  "net.ruippeixotog" %% "scala-scraper" % "1.2.0",
  "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.0-akka-2.11"
)



fork in run := true
