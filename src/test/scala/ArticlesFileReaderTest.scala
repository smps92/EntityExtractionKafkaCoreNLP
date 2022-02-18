import java.io.File

import org.scalatest.FunSuite
import kafkaStreams.ArticlesFileReader

class ArticlesFileReaderTest extends FunSuite {

  test("get articles should return articles ") {
    val path = getClass.getResource("/articles.txt").getPath

    val articleFileReader = new ArticlesFileReader(path)
    val articles = articleFileReader.getArticles()
    assert(articles == List[String]("This is an article"))
  }
}
