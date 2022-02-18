package kafkaStreams

trait ArticlesInputReader {

  def getArticles(): List[String]

}
