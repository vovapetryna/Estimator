val scalaSettings = Seq(
  scalaVersion := "2.12.4",
  version := "0.2",
  name := "Estimator",
  organization := "vp"
)

val akkaVersion            = "2.6.8"
val akkaHttpVersion        = "10.2.2"
val akkaSessionVersion     = "0.5.5"
val endpointAlgebraVersion = "0.5.0"
val slickVersion           = "3.2.1"
val commonSettings = scalaSettings ++ Seq(
  libraryDependencies ++= Seq(
    "com.typesafe.scala-logging" %% "scala-logging"  % "3.5.0",
    "com.github.pureconfig"      %% "pureconfig"     % "0.10.0",
    "ch.qos.logback"             % "logback-classic" % "1.1.7",
    "com.lihaoyi"                %% "upickle"        % "0.9.5"
  )
)

lazy val shared = Project(id = "shared", file("modules/shared"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.julienrf" %% "endpoints-algebra" % endpointAlgebraVersion
    )
  )

lazy val core = Project(id = "core", file("modules/core"))
  .dependsOn(shared)

lazy val postgresql = Project(id = "postgresql", file("modules/postgresql"))
  .dependsOn(core)
  .settings(resolvers += Resolver.jcenterRepo)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.slick"  %% "slick"               % slickVersion,
      "com.typesafe.slick"  %% "slick-hikaricp"      % slickVersion,
      "com.github.tminglei" %% "slick-pg"            % "0.19.4",
      "io.github.nafg"      %% "slick-migration-api" % "0.4.2"
    )
  )

lazy val akkaExt = Project(id = "akkaExt", file("modules/akkaExt"))
  .dependsOn(postgresql)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka"                  %% "akka-actor-typed"           % akkaVersion,
      "com.typesafe.akka"                  %% "akka-http"                  % akkaHttpVersion,
      "com.typesafe.akka"                  %% "akka-stream"                % akkaVersion,
      "org.julienrf"                       %% "endpoints-akka-http-server" % endpointAlgebraVersion,
      "com.softwaremill.akka-http-session" %% "core"                       % akkaSessionVersion,
      "de.mkammerer"                       % "argon2-jvm"                  % "2.7",
      "ch.megard"                          %% "akka-http-cors"             % "1.1.1"
    )
  )

lazy val api = Project(id = "api", file("api"))
  .dependsOn(akkaExt)
  .enablePlugins(JavaAppPackaging, DockerPlugin)
  .settings(
    dockerBaseImage := "openjdk:11",
    dockerRepository := Some("vovapetryna"),
    dockerExposedPorts := Seq(9001, 9001),
    version in Docker := "0.1.1-SNAPSHOT"
  )
  .settings(mappings in (Compile, packageDoc) := Seq())
  .settings(unmanagedResourceDirectories in Compile += (sourceDirectory.value / "../../app/build"))
  .settings(
    libraryDependencies ++= Seq(
      "com.softwaremill.macwire" %% "macros" % "2.3.1" % "provided"
    )
  )

lazy val root = Project(id = "Estimator", base = file("."))
  .aggregate(api)

addCommandAlias("api", ";project api; run")
