package scalaest

trait Admin {
  /** @return names zipped with a score based on the content 
      of search results for the name */
  def admin(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestAdmin extends SequentialAdmin

class SequentialAdmin extends Admin {
  def admin(names: Iterable[String]) = names map { name => 
    (name, (0 /: ScalaestProfiler.urls(name).map { url =>
      ScalaestJudge.judge(url)
    }) { _ + _ } )
  }
}
