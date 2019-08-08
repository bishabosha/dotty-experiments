val dottyVersion = "0.17.0-RC1"
// val dottyVersion = dottyLatestNightlyBuild.get

lazy val root = project
  .in(file("."))
  .settings(
    name := "fp",
    version := "0.1.0",
    scalaVersion := dottyVersion,
    updateOptions := updateOptions.value.withLatestSnapshots(false),

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    // libraryDependencies += "ch.epfl.lamp" % "dotty-sbt-bridge" % "0.17.0-bin-SNAPSHOT"
  )
