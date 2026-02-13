package edu.luc.cs.cs371.topwords

import scala.collection.mutable

//stdin args + Observer Object 
class TopWordsProcessor(
  cloudSize: Int,
  minLength: Int,
  windowSize: Int,
  observer: WordCloudObserver
  ):
  private val window = mutable.Queue[String]()//FIFO
  private val freqs = mutable.Map[String, Int]().withDefaultValue(0)
  private val wordPositions = mutable.Map[String, Int]().withDefaultValue(0) // track when each word was last added
  private var currentPos = 0 // global position counter

  def processWord(word: String): Unit =
    //word length not long enough to count 
    if word.length < minLength then
      return
    //add word to end of window and increase counters
    window.enqueue(word)
    freqs(word) += 1
    wordPositions(word) = currentPos
    currentPos += 1
    
    // Generate update when window overflows (before evicting)
    if window.size > windowSize then
      val wordCloud = generateWordCloud()
      observer.onUpdate(wordCloud)
      
      // Evict oldest word
      val evict = window.dequeue()
      freqs(evict) -= 1
      if (freqs(evict) == 0) then 
        freqs.remove(evict)
    // Also generate update when window first reaches capacity
    else if window.size == windowSize then
      val wordCloud = generateWordCloud()
      observer.onUpdate(wordCloud)



  private def generateWordCloud(): List[(String, Int)] =
    freqs
      .toList
      .sortBy(x => (-x._2, -wordPositions(x._1)))//sort by freq desc, then by recency desc
      .take(cloudSize)
