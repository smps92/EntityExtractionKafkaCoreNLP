package kafkaStreams
import java.util.Properties

import org.apache.kafka.clients.producer._
import java.util.Properties
import scala.collection.immutable.HashMap


class ArticlesProducer(config: HashMap[String, String], reader: ArticlesInputReader) {

  val props: java.util.Properties = new Properties()
  props.setProperty("bootstrap.servers", config.getOrElse("bootstrapServers", "127.0.0.1:9092"))
  props.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val kafkaTopic = config.getOrElse("kafkaTopic", "extractEntitiesInput")
  val producer = new KafkaProducer[String, String](props)

  def sendRecord(text: String) = {

    val producerRecord = new ProducerRecord[String, String](kafkaTopic, text)
    producer.send(producerRecord)
  }

  def flush(): Unit = {
    producer.flush()
  }

  def produceArticlesToKafka() = {
    for (article <- reader.getArticles()) {
      sendRecord(article)
    }
    flush()
  }

}
