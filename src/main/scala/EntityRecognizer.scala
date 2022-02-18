package kafkaStreams

trait EntityRecognizer {

  def getRecognizedEntities(text: String): List[String]
}
