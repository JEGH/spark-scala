package local.scala.sparkKafka

import java.util.Properties

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka010._

object ConsumerExample2 {
  def main(args: Array[String]) {

    var totalCount  = 0L
    val sparkConf   =  new SparkConf().setMaster("local[1]").setAppName("AnyName").set("spark.driver.host", "localhost")
    val ssc         =  new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")

    val kafkaParams = Map(
      "bootstrap.servers"  -> "localhost:9092",
      "key.deserializer"   -> "org.apache.kafka.common.serialization.StringDeserializer",
      "value.deserializer" -> "org.apache.kafka.common.serialization.StringDeserializer",
      "group.id"           -> "something"
    )
    // List of topics you want to listen for from Kafka
    val topics = List("test").toSet
    // Create our Kafka stream, which will contain (topic,message) pairs. We tack a
    // map(_._2) at the end in order to only get the messages, which contain individual
    // lines of data.
    val TOPIC="test"  //  Zookeeper and kafka must installed and this topic created!!

    val  props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "something")


    val stream = KafkaUtils.createDirectStream[String, String] (ssc, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topics,  kafkaParams ))


    stream.foreachRDD((rdd: RDD[_], time: Time) => {
      val count = rdd.count()
      println("\n-------------------")
      println("Time: " + time)
      println("-------------------")
      println("Received " + count + " events\n")
      totalCount += count
    })
    ssc.start()
    Thread.sleep(20 * 1000)
    ssc.stop()

    if (totalCount > 0) {
      println("PASSED")
    } else {
      println("FAILED")
    }
  }
}
