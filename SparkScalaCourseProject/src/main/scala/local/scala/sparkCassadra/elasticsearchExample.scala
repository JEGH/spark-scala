package local.scala.sparkCassadra

import org.apache.spark.{SparkConf, SparkContext}
import org.elasticsearch.spark.rdd.EsSpark


object elasticsearchExample extends App{
  print("hello ElasticSearch!")

  // initialise spark context with elasticSearchParameters
  //val conf = new SparkConf().setAppName("helloElasticSearch")
  val conf = new SparkConf().setMaster("local")
  .setAppName("WordCount-ElasticSearch")
  .set("spark.es.nodes","localhost")
  .set("spark.es.port","9200")
  //.set("spark.es.nodes.discovery","ture")
  //.set("spark.es.nodes.client.only","false")
  val sc = new SparkContext(conf)
  println(sc)



  val textFile = sc.textFile("src/main/resources/shakespeare.txt")

  //word count
  val counts = textFile.flatMap(line => line.split(" "))
    .map(word => (word, 1))
    .reduceByKey(_ + _)



  // Write elasticsearch
  EsSpark.saveToEs(counts, "spark2/docs1")


}
