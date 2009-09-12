package scalaest

trait Profiler {
  /** @return urls related to the given name */
  def urls(name: String): Iterable[String]
}

object ScalaestProfiler extends MockProfiler

class MockProfiler extends Profiler {
  def urls(name: String) = "http://www.scala-lang.org/" :: Nil
}