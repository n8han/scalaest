package scalaest

trait Counter {
  val word = "scala"
  def count(inStr: String): Int
}

object ScalaestCounter extends MockCounter

class MockCounter extends Counter {
  def count(inStr: String) = 0
}


