package local.scala.drafts

import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object KafkaSparkTest {

  def main(args: Array[String]) {
    if (args.length != 7) {
      System.err.println(
        "Usage: StationJourneyCountApp <appname> <brokerUrl> <topic> <consumerGroupId> <zkQuorum> <checkpointDir> <outputPath>")
      System.exit(1)
    }

    val Seq(appName, brokerUrl, topic, consumerGroupId, zkQuorum, checkpointDir, outputPath) = args.toSeq

    val conf = new SparkConf()
      .setAppName(appName)
      .setJars(SparkContext.jarOfClass(this.getClass).toSeq)

    val ssc = new StreamingContext(conf, Seconds(10))
    ssc.checkpoint(checkpointDir)

    val topics = Set(topic)
    val params = Map[String, String](
      "zookeeper.connect" -> zkQuorum,
      "group.id" -> consumerGroupId,
      "bootstrap.servers" -> brokerUrl)

     val test = KafkaUtils.createDirectStream[String, String] (ssc, LocationStrategies.PreferConsistent,
       ConsumerStrategies.Subscribe[String, String](topics,  params))

    test.foreachRDD(rdd =>
        println(rdd)
    )

    ssc.start()
    ssc.awaitTermination()
  }

}