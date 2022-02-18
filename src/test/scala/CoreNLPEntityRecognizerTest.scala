package kafkaStreams

import org.scalatest.FunSuite
import kafkaStreams.CoreNLPEntityRecognizer
import scala.collection.immutable.HashMap

class CoreNLPEntityRecognizerTest extends FunSuite {

  test("coreNLPRecognizer getRecognized entities should return valid entities") {

    val entityRecognizer = new CoreNLPEntityRecognizer(new HashMap[String, String]())
    val entities = entityRecognizer.getRecognizedEntities("Citibank is a bank in the United States. " +
      "It is also present in Singapore")
    val recognizerTime = System.currentTimeMillis()
    assert(entities == List[String]("Citibank", "United States", "Singapore"))
  }

}
