package scalaest

trait Admin {
  /** oversee the judging process for each name given, return final tally */
  def admin(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestAdmin extends WaitingAdmin

class WaitingAdmin extends Admin {
  def admin(names: Iterable[String]) = {
    ConcurrentAdmin !? Gather(names) match {
      case Results(results) => results
    }
  }
}

import actors._
import Actor._

object ConcurrentAdmin extends Actor {
  var scores = List[(String, Int)]()
  var names = List[String]()
  var waiter: Option[OutputChannel[Any]] = None
  def act =
    loop { receive {
      case Gather(in) =>
        names = in.toList
        waiter = Some(sender)
        names foreach { name => new ConcurrentProfiler(this, name) }
      case Score(name, count) => 
        scores = (name, count) :: scores
        if (scores.length == names.length) {
          waiter foreach { _ ! Results(scores) }
        }
        println("Finished %d / %d" format (scores.length, names.length))
    } }
  start()
}

class ConcurrentProfiler(admin: Actor, name: String) extends Actor {
  var score = 0
  var judges = List[OutputChannel[Any]]()
  def act = {
    judges = ScalaestProfiler.urls(name).map { url =>
      new ConcurrentJudge(this, url)
    }.toList
    loop { receive {
      case Add(count) =>
        score += count
        judges -= sender
        if (judges.isEmpty) {
          admin ! Score(name, score)
          exit()
        }
    } }
  }
  start()
}

class ConcurrentJudge(profiler: Actor, url: String) extends Actor {
  def act = {
    profiler ! Add(ScalaestJudge.judge(url))
    exit()
  }
  start()
}

case class Gather(names: Iterable[String])
case class Profile(name: String)
case class Add(count: Int)
case class Score(name: String, count: Int)
case class Results(scores: Iterable[(String, Int)])

class SequentialAdmin extends Admin {
  def admin(names: Iterable[String]) = names map { name => 
    (name, (0 /: ScalaestProfiler.urls(name).map { url =>
      ScalaestJudge.judge(url)
    }) { _ + _ } )
  }
}
