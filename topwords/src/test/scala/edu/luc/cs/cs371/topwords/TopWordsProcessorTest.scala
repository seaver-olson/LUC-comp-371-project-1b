package edu.luc.cs.cs371.topwords

class TopWordsProcessorTest:
  val observer = new WordCloudObserver

  val processor = TopWordsProcessor(
      cloudSize = 10,
      minLength = 6, 
      windowSize = 1000,
      observer = observer
    )
