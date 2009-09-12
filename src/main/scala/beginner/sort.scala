package scalaest

trait Sorter {
  def sort(in: Iterable[(String, Int)]): Seq[(String, Int)]
}

object ScalaestSorter extends SolutionSorter

class SolutionSorter extends Sorter {
  def sort(in: Iterable[(String, Int)]) = in.toList.sort {
    case ((name1, score1), (name2, score2)) => score1 < score2
  }
}

class MockSorter extends Sorter {
  def sort(in: Iterable[(String, Int)]) = in.toList
}