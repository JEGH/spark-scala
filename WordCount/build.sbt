name := "WordCount"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.2.0",
  "org.apache.spark" %% "spark-streaming" % "2.2.0",
  //Need this package to work correctly
  "commons-lang" % "commons-lang" % "2.6"
)
/*

To run this file, go to project folder and type:

> sbt-assembly

The output file (JAR) can be found at /src/target/scala-2.11/WordCount-assembly-0.1.jar

 */