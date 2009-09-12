package scalaest

trait Judge {
  /** @return names zipped with a score based on the content 
      of search results for the name */
  def judge(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestJudge extends SolutionJudge

class SolutionJudge extends Judge {
  import scala.xml.XML
  import scala.io.Source
  def judge(names: Iterable[String]) = names map { name =>
    println("Researching %s..." format name)
    (name, 
      (XML.load("http://boss.yahooapis.com/ysearch/web/v1/" + java.net.URLEncoder.encode(name, "utf8") + 
        "?appid=afMQBhfV34GPV8xJ7jVsjQUWTgsn.h9RybhtZxPpJf4cQguR.cAxdRnJ6lk0BEh1&format=xml"
      ) \\ "url").elements.flatMap { node => 
        println("Referencing %s..." format node.text)
        try {
          Source.fromURL(node.text).getLines
        } catch {
          case _ => println("Fail."); Nil.elements
        }
      }.foldLeft(0) {
        _ + ScalaestCounter.count("scala", _)
      }
    )
  }
}

class MockJudge extends Judge {
  def judge(names: Iterable[String]) = names map { nm => 
    (nm, ScalaestCounter.count("scala", "everybody loves scala"))
  }
}
