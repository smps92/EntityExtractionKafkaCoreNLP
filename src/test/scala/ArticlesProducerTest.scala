package kafkaStreams
import scala.collection.immutable.HashMap

class ArticlesProducerTest {

  test("produce articles to Kafka") {
    val articlesInputReader = new ArticlesInputReader {
      override def getArticles(): List[String] = {
        val mockArticleTexts = List[String]("This is an article, United States, Singapore", "Singapore, United States")
        return mockArticleTexts
      }
    }
    val conf = new HashMap[String, String]()
    val articlesProducer = new ArticlesProducer(conf, articlesInputReader)
    articlesProducer.produceArticlesToKafka()
  }

}
