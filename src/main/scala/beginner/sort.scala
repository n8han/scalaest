package scalaest

trait Sorter {
  def sort(in: Iterable[(String, Int)]): Seq[(String, Int)]
}

object ScalaestSorter extends MockSorter

class MockSorter extends Sorter {
  def sort(in: Iterable[(String, Int)]) = in.toList
}