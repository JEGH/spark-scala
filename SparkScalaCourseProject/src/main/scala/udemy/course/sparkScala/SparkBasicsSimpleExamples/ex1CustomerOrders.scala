package udemy.course.sparkScala.SparkBasicsSimpleExamples

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

object ex1CustomerOrders {

  def parseLine(line: String) = {
    // Split by commas
    val fields = line.split(",")
    // Extract the age and numFriends fields, and convert to integers
    //(costumerID , itemID, spent
    val costumerID = fields(0).toInt
    val spent = fields(2).toFloat
    // Create a tuple that is our result.
    (costumerID, spent)

  }
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "customer-orders-amountSpent2")

    // Load each line of the source data into an RDD
    val lines = sc.textFile("src/main/resources/customer-orders.csv")

    // Use our parseLines function to convert to (costumerID, spent) tuples
    val rdd = lines.map(parseLine)

    //rdd.collect().foreach(println)



    val rdd2 = rdd.reduceByKey(  (x,y) => (x +y)  )

    //val rdd4 = rdd3.mapValues( x => (x._1,_)  )

    rdd2.collect().foreach(println)

  }

}
