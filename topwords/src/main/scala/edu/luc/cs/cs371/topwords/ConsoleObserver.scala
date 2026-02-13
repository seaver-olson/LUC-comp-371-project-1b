package edu.luc.cs.cs371.topwords

import com.typesafe.scalalogging.StrictLogging

class ConsoleObserver(minFrequency: Int = 0) extends WordCloudObserver with StrictLogging:
  
  def onUpdate(wordCloud: List[(String, Int)]): Unit =
    // Filter by minimum frequency and format output
    val filtered = wordCloud.filter(_._2 >= minFrequency)
    if filtered.nonEmpty then
      val output = filtered.map { case (word, freq) => s"$word: $freq" }.mkString(" ")
      println(output)
      logger.debug(s"Output: $output")
