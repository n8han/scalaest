package scalaest

trait Counter {
  def count(word: String, inStr: String)
}

object ScalaestCounter extends MockCounter

class MockCounter extends Counter {
  def count(word: String, inStr: String) = inStr.length / 100
}


