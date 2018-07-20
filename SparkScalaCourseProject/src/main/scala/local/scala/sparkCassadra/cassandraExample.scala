package local.scala.sparkCassadra
import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}
object cassandraExample extends App {
  print("hello cassadra!")
  val conf = new SparkConf(true)
    .set("spark.cassandra.connection.host", "localhost")
    .set("spark.cassandra.auth.username", "cassandra")
    .set("spark.cassandra.auth.password", "cassandra")



  val sc = new SparkContext("local[*]", "test", conf)

  val rdd = sc.cassandraTable("test", "kv")
  println(rdd.count)
  println(rdd.first)
  println(rdd.map(_.getInt("value")).sum)


}
