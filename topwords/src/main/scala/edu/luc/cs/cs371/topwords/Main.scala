package edu.luc.cs.cs371.topwords

import com.typesafe.scalalogging.StrictLogging
import mainargs.{arg, main}
import scala.io.Source

object Main extends StrictLogging:
  @main
  def topwords(
    @arg(name = "cloud-size", short = 'c', doc = "Number of words in the cloud") 
    cloudSize: Int = 10,
    @arg(name = "length-at-least", short = 'l', doc = "Minimum word length to consider")
    minLength: Int = 6,
    @arg(name = "window-size", short = 'w', doc = "Size of the moving window")
    windowSize: Int = 1000,
    @arg(name = "every-k-steps", short = 'k', doc = "Generate update every k steps (0 = every step)")
    everyKSteps: Int = 10,
    @arg(name = "min-frequency", short = 'f', doc = "Minimum frequency to include in cloud")
    minFrequency: Int = 3
  ): Unit =
    try
      logger.debug(s"howMany=$cloudSize minLength=$minLength lastNWords=$windowSize everyKSteps=$everyKSteps minFrequency=$minFrequency")
      
      val observer = new ConsoleObserver(minFrequency)
      val processor = new TopWordsProcessor(cloudSize, minLength, windowSize, observer)
      
      logger.debug("Reading words from standard input...")
      
      for line <- Source.stdin.getLines() do
        val words = line.trim.split("(?U)[^\\p{Alpha}0-9']+").filter(_.nonEmpty)
        words.foreach(word => processor.processWord(word.toLowerCase))
      
      logger.debug("Processing complete")
    catch
      case e: Exception =>
        logger.error("Error during processing", e)
        System.exit(1)
