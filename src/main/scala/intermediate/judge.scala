package scalaest

trait Judge {
  def judge(url: String): Int
}

object ScalaestJudge extends MockJudge

class MockJudge extends Judge {
  def judge(url: String) = ScalaestCounter.count("scala scala scala escalada scaloo scala")
}