package scalaest

trait Judge {
  def judge(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestJudge extends MockJudge

class MockJudge extends Judge {
  def judge(names: Iterable[String]) = names map { nm => 
    (nm, ScalaestCounter.count("scala", "everybody loves scala"))
  }
}
