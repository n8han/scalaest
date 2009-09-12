package scalaest

trait Judge {
  /** Use ScalaestCounter on the contents of the given url */
  def judge(url: String): Int
}

object ScalaestJudge extends MockJudge

class MockJudge extends Judge {
  def judge(url: String) = ScalaestCounter.count("scala scala scala escalada scaloo scala")
}