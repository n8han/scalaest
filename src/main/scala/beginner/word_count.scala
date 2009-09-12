package scalaest

trait Counter {
  def count(word: String, inStr: String): Int
}

object ScalaestCounter extends SolutionCounter

class SolutionCounter extends Counter {
  def count(word: String, inStr: String) = 
    ("""(?i)\b%s\b""" format word).r.findAllIn(inStr).toList.size
}

class MockCounter extends Counter {
  def count(word: String, inStr: String) = 0
}


