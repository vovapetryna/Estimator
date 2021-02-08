val scalaSettings = Seq(
  scalaVersion := "2.12.4",
  version := "0.1",
  name := "Estimator",
  organization := "vp"
)

val commonSettings = scalaSettings ++ Seq(
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging"  % "3.5.0",
    "com.github.pureconfig"      %% "pureconfig"     % "0.10.0",
    "ch.qos.logback"             % "logback-classic" % "1.1.7",
    "com.lihaoyi"                %% "upickle"        % "0.9.5"
  )
)

lazy val core = Project(id = "core", file("modules/core"))
  .settings(commonSettings: _*)

lazy val postgresql = Project(id = "postgresql", file("modules/postgresql"))
  .settings(commonSettings: _*)
  .dependsOn(core)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.slick"  %% "slick"    % "3.2.1",
      "com.github.tminglei" %% "slick-pg" % "0.16.0"
    )
  )

val akkaVersion     = "2.6.8"
val akkaHttpVersion = "10.2.2"
lazy val api = Project(id = "api", file("api"))
  .settings(commonSettings: _*)
  .dependsOn(postgresql)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed"  % akkaVersion,
      "com.typesafe.akka" %% "akka-stream-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"       % akkaVersion,
      "com.typesafe.akka" %% "akka-http"         % akkaHttpVersion
    )
  )

lazy val root = Project(id = "Estimator", base = file("."))
  .settings(scalaSettings: _*)
  .aggregate(api)

addCommandAlias("api", ";project api; ~run")
