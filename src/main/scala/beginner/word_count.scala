package scalaest

trait Counter {
  val word = "scala"
  def count(inStr: String): Int
}

object ScalaestCounter extends SolutionCounter

class SolutionCounter extends Counter {
  def count(word: String, inStr: String) = 
    ("""(?i)\b%s\b""" format word).r.findAllIn(inStr).toList.size
}

class MockCounter extends Counter {
  def count(inStr: String) = 0
}


