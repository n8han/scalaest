package scalaest

trait Judge {
  /** Use ScalaestCounter on the contents of the given url */
  def judge(url: String): Int
}

object ScalaestJudge extends SolutionJudge

class SolutionJudge extends Judge {
  import scala.io.Source
  def judge(url: String) = {
    println("Referencing %s..." format url)
    try {
      val uc =  new java.net.URL(url).openConnection
      uc.setConnectTimeout(10000)
      uc.setReadTimeout(10000)
      (0 /: Source.fromInputStream(uc.getInputStream).getLines) {
        _ + ScalaestCounter.count(_)
      }
    } catch {
      case e => println(e.getMessage); 0
    }
  }
}

class MockJudge extends Judge {
  def judge(url: String) = ScalaestCounter.count("scala scala scala escalada scaloo scala")
}