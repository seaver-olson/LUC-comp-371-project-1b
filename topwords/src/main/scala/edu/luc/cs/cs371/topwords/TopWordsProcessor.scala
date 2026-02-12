package edu.luc.cs.cs371.topwords

import scala.collection.mutable

// cloudSize - num of top words to include
// minLength - minimum word length to consider
// windowSize - size of sliding window
// observer - observer to notify on updates
class TopWordsProcessor(
  cloudSize: Int,
  minLength: Int,
  windowSize: Int,
  observer: WordCloudObserver
  ):
  private val window = mutable.Queue[String]()
  private val freqs = mutable.Map[String, Int]().withDefaultValue(0)
  private var wordCount = 0

  def processWord(word: String): Unit =
    ()

  private def generateWordCloud(): List[(String, Int)] =
    List.empty
