import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  
  lazy val scalactic = "org.scalactic" %% "scalactic" % "3.0.1"
  
  lazy val breeze = "org.scalanlp" %% "breeze" % "0.12"
  lazy val breezeNatives = "org.scalanlp" %% "breeze-natives" % "0.12"
  lazy val breezeViz = "org.scalanlp" %% "breeze-viz" % "0.12"

  
  
  lazy val breezeSnapshotResolver = "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
  lazy val breezeReleasesResolver = "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"

}
