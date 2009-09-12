package scalaest

trait Admin {
  /** @return names zipped with a score based on the content 
      of search results for the name */
  def admin(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestAdmin extends SolutionAdmin

class SolutionAdmin extends Admin {
  import scala.xml.XML
  def admin(names: Iterable[String]) = names map { name =>
    println("--- Researching %s ---" format name)
    (name, 
      (XML.load("http://boss.yahooapis.com/ysearch/web/v1/" + java.net.URLEncoder.encode(name, "utf8") + 
        "?appid=afMQBhfV34GPV8xJ7jVsjQUWTgsn.h9RybhtZxPpJf4cQguR.cAxdRnJ6lk0BEh1&format=xml"
      ) \\ "url").map { node => ScalaestJudge.judge(node.text) }.foldLeft(0) { _ + _ }
    )
  }
}

class MockAdmin extends Admin {
  def admin(names: Iterable[String]) = names map { name => 
    (name, (0 /: ScalaestProfiler.urls(name).map { url =>
      ScalaestJudge.judge(url)
    }) { _ + _ } )
  }
}
