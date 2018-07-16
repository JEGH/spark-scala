name := "SparkScalaCourseProject"
version := "0.1"
scalaVersion := "2.11.0"

scalaVersion := "2.11.0"
//scalaVersion := "2.11.8"
val hadoopVersion = "2.6.0"
val sparkVersion = "2.2.0"
val kafkaVersion = "0.10.2.0"
val clouderaVersion = "5.14.2"
val kuduVersion = "1.6.0"
//val cassandraConnectorVersion = "2.0.7"
//val json4sNative = "org.json4s" %% "json4s-native" % "{latestVersion}"
//val json4sJackson = "org.json4s" %% "json4s-jackson" % "{latestVersion}"

scalacOptions += "-Yresolve-term-conflict:package"

resolvers ++= Seq(
  "Cloudera Releases" at "http://repository.cloudera.com/artifactory/cloudera-repos/"
)


//libraryDependencies += "org.apache.storm" % "storm-core" % stormVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-streaming" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion

//libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % sparkVersion

//cloudera
//libraryDependencies += "com.cloudera.spark" %% "spark-core" % sparkVersion



libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7"

//libraryDependencies += "org.apache.kudu:kudu-spark2_2.11:1.4.0"
//libraryDependencies += "org.apache.kudu" %% "kudu-spark" % "1.6.0-cdh5.14.2"

// merge strategy
/*
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
*/

//addSbtPlugin("pt.nos.parceiros.devbi.utils" % "sbt-assembly" % "0.14.3")
