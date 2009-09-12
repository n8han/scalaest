package scalaest

trait Counter {
  val word = "scala"
  def count(inStr: String): Int
}

object ScalaestCounter extends SolutionCounter

class SolutionCounter extends Counter {
  val rx = ("""(?i)\b%s\b""" format word).r
  def count(inStr: String) = rx.findAllIn(inStr).toList.size
}

class MockCounter extends Counter {
  def count(inStr: String) = 0
}


