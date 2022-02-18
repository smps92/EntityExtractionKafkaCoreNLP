package kafkaStreams

import edu.stanford.nlp.pipeline.{CoreDocument, StanfordCoreNLP}
import java.util.Properties
import scala.collection.immutable.HashMap
import scala.collection.immutable.List
import scala.collection.JavaConverters._

class CoreNLPEntityRecognizer(config: HashMap[String, String]) extends EntityRecognizer {

  val props: java.util.Properties = new Properties()
  props.setProperty("annotators", "tokenize,ssplit, pos, lemma,ner")
  props.setProperty("ner.useSUTime", "false")
  props.setProperty("ner.applyNumericClassifiers", "false")

  val pipeline = new StanfordCoreNLP(props)

  def getRecognizedEntities(text: String): List[String] = {
    val document = new CoreDocument(text)
    pipeline.annotate(document)

    val entities = for {
      entity <- document.entityMentions().asScala.toList
    } yield entity.text()

    return entities
  }
}