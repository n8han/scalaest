package scalaest

trait Judge {
  /** @return names zipped with a score based on the content 
      of search results for the name */
  def judge(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestJudge extends MockJudge

class MockJudge extends Judge {
  def judge(names: Iterable[String]) = names map { nm => 
    (nm, ScalaestCounter.count("everybody loves scala"))
  }
}
