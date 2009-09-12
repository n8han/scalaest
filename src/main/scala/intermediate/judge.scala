package scalaest

trait Judge {
  def judge(url: String): Int
}

object ScalaestJudge extends SolutionJudge

class SolutionJudge extends Judge {
  import scala.io.Source
  def judge(url: String) = {
    println("Referencing %s..." format url)
    try {
      (0 /: Source.fromURL(url).getLines) {
        _ + ScalaestCounter.count(_)
      }
    } catch {
      case e => println(e.getMessage); 0
    }
  }
}

class MockJudge extends Judge {
  def judge(url: String) = ScalaestCounter.count("scala scala scala")
}