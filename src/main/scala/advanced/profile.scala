package scalaest

trait Profiler {
  def urls(name: String): Iterable[String]
}

object ScalaestProfiler extends MockProfiler

class MockProfiler extends Profiler {
  def urls(name: String) = "http://www.scala-lang.org/" :: Nil
}