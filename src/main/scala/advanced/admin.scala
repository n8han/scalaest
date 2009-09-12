package scalaest

trait Admin {
  /** oversee the judging process for each name given, return final tally */
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
