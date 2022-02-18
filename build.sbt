name := "EntityExtractionKafka"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "3.0.0"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "3.0.0"
libraryDependencies += "org.apache.kafka" % "kafka-streams-scala" % "3.0.0"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "4.3.0" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp"))

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

