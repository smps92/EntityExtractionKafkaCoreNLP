package kafkaStreams

import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.kstream.ValueMapper

import scala.collection.immutable.HashMap
import java.util.Properties

import org.apache.kafka.streams.kstream.{KStream, KTable, Materialized}

class KafkaEntityRecognizer (config: HashMap[String, String], entityRecognizer: EntityRecognizer)  {

  def recognizeEntities(): Unit = {

    val props: java.util.Properties = new Properties()
    props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, config.getOrElse("bootstrapServers", "127.0.0.1:9092"))
    val builder = new StreamsBuilder
    val textStream = builder.stream[String, String](config.getOrElse("inputTopic", "entityExtractionInput"))
    val streams = new KafkaStreams(builder.build(), props)

    val mapper: ValueMapper[String, String] = new ValueMapper[String, String] {
      override def apply(text: String): String = {
        return entityRecognizer.getRecognizedEntities(text).mkString(",")
      }
    }
    textStream.mapValues(mapper).to("entityExtractionOutput")
  }
}
