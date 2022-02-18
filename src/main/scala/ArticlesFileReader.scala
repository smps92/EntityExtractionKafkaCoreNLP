
package kafkaStreams

class ArticlesFileReader(iFile: String) extends ArticlesInputReader {

  val inputFile = iFile

  override def getArticles(): List[String] = {
    val source = scala.io.Source.fromFile(inputFile)
    val articleTexts = source.getLines().toList
    source.close()
    return articleTexts
  }

}
