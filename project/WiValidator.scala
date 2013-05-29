import sbt._
import sbt.Keys._


object WiValidatorBuild extends Build {

  val junitV = "4.11"
  val cucumberV = "1.1.2"
  val scalatestV = "2.0.M5b"
  val scalaV = "2.10.1"

  lazy val wiValidator = Project(
    id = "wiValidator",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "wiValidator",
      organization := "es.weso",
      version := "0.0.1-SNAPSHOT",
      scalaVersion := scalaV,
      
      //Test Dependencies
      libraryDependencies += "junit" % "junit" % junitV,
      libraryDependencies += "org.scalatest" %% "scalatest" % scalatestV,
      libraryDependencies += "info.cukes" % "cucumber-jvm" % cucumberV,
      libraryDependencies += "info.cukes" % "cucumber-core" % cucumberV,
      libraryDependencies += "info.cukes" % "cucumber-scala" % cucumberV,
      libraryDependencies += "info.cukes" % "cucumber-junit" % cucumberV,
      libraryDependencies += "org.seleniumhq.selenium" % "selenium-java" % "2.32.0",
      
      //Project Dependencies
      libraryDependencies += "org.apache.poi" % "poi" % "3.9",
      
      //Resolvers
      resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
      resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/")
    )
}
