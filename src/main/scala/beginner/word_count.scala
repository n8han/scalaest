package scalaest

trait Counter {
  val word = "scala"
  /** count the number of times "scala" appears in the given string, 
      case insensitive, and not as part of a longer word */
  def count(inStr: String): Int
}

object ScalaestCounter extends MockCounter

class MockCounter extends Counter {
  def count(inStr: String) = 0
}


