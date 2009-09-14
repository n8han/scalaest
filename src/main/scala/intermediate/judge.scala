package scalaest

trait Judge {
  /** Use ScalaestCounter on the contents of the given url */
  def judge(url: String): Int
}

object ScalaestJudge extends SolutionJudge

class SolutionJudge extends Judge {
  import dispatch._
  import Http._
  import scala.io.Source
  def judge(url: String) = {
    try {
      (new Http)(url >~ { so =>
        (0 /: so.getLines) { _ + ScalaestCounter.count(_) }
      })
    } catch {
      case e => println(e.getMessage); 0
    }
  }
}

class MockJudge extends Judge {
  def judge(url: String) = ScalaestCounter.count("scala scala scala escalada scaloo scala")
}