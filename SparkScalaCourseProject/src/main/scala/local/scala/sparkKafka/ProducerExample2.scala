package local.scala.sparkKafka

import java.util.Properties

import org.apache.kafka.clients.producer._

object ProducerExample2 {
  def main(args: Array[String]) {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val TOPIC = "test"

    for (i <- 1 to 50) {
      Thread.sleep(1000) //every 1 second
      val record = new ProducerRecord(TOPIC, "key", "the end "+new java.util.Date)
      producer.send(record)
    }

    producer.close()
  }
}
