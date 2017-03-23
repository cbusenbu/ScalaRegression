import Dependencies._

lazy val root = (project in file(".")).
  settings(
      organization := "edu.bsu",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT",
      name := "Regression",
      libraryDependencies += scalaTest % Test,
      libraryDependencies += scalactic,
      libraryDependencies ++= Seq(breeze,breezeNatives,breezeViz),
      //libraryDependencies += breezeNatives,
      //libraryDependencies += breezeViz,

      resolvers ++= Seq(breezeSnapshotResolver,breezeReleasesResolver)
  )

