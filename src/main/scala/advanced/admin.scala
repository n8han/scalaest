package scalaest

trait Admin {
  /** @return names zipped with a score based on the content 
      of search results for the name */
  def admin(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestAdmin extends MockAdmin

class MockAdmin extends Admin {
  def admin(names: Iterable[String]) = names map { nm => 
    (nm, ScalaestJudge.judge("http://www.scala-lang.org/"))
  }
}
