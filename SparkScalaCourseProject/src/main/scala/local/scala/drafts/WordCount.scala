package local.scala.drafts

import org.apache.spark.{SparkConf, SparkContext}

object WordCount extends App {
  System.setProperty("hadoop.home.dir", "C:\\winutil\\")
  //def main(args: Array[String]): Unit = {
    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)
    println(sc)
    // Load the text into a Spark RDD, which is a distributed representation of each line of text
    val textFile = sc.textFile("src/main/resources/shakespeare.txt")

    //word count
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.foreach(println)
    System.out.println("Total words: " + counts.count());
    counts.saveAsTextFile("/tmp/shakespeareWordCount-test1");
  //}
}