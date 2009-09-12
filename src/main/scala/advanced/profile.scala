package scalaest

trait Profiler {
  def urls(name: String): Iterable[String]
}

object ScalaestProfiler extends SolutionProfiler

class SolutionProfiler extends Profiler {
  import scala.xml.XML
  def urls(name: String) = {
    println("--- Researching %s ---" format name)
    (XML.load("http://boss.yahooapis.com/ysearch/web/v1/" + java.net.URLEncoder.encode(name, "utf8") + 
      "?appid=afMQBhfV34GPV8xJ7jVsjQUWTgsn.h9RybhtZxPpJf4cQguR.cAxdRnJ6lk0BEh1&format=xml"
    ) \\ "url") map { _.text }
  }
}
class MockProfiler extends Profiler {
  def urls(name: String) = "http://www.scala-lang.org/" :: Nil
}