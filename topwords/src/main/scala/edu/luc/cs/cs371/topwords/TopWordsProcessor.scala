package edu.luc.cs.cs371.topwords

class TopWordsProcessor(
  cloudSize: Int,
  minLength: Int,
  windowSize: Int,
  observer: WordCloudObserver
  ):
  private val window 
  private val freqs
  private var wordCount = 0

  def processWord(word: String): Unit =
    ()

  private def generateWordCloud(): List[(String, Int)] =
    List.empty
