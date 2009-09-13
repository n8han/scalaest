package scalaest

trait Admin {
  /** oversee the judging process for each name given, return final tally */
  def admin(names: Iterable[String]): Iterable[(String, Int)]
}

object ScalaestAdmin extends WaitingAdmin

class WaitingAdmin extends Admin {
  def admin(names: Iterable[String]): Iterable[(String, Int)] = {
    
  }
}

class ConcurrentAdmin(names: Iterable[String]) extends Actor {
  var scores = List[String, Int]()
  def act = loop {
    react {
      case Score(name, count) => 
        scores = (name, count) :: scores
        if (scores.length == names.length)
      case Complete(username) =>
        
        final_scores += (username, s)
    }
  }
}

class ConcurrentProfiler(admin: Actor, name: String) extends Actor {
  var score = 0
  var judges = Set[Actor]()
  def act = {
    judges = ScalaestProfiler.urls(name) map { url =>
      new ConcurrentJudge(this, url)
    }
    loop {
      react {
        case Add(count) =>
          score += count
          judges -= sender
          if (judges.empty) {
            admin ! score(name, score)
            exit()
          }
      }
    }
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

case class Add(count: Int)
case class Score(name: String, count: Int)

class SequentialAdmin extends Admin {
  def admin(names: Iterable[String]) = names map { name => 
    (name, (0 /: ScalaestProfiler.urls(name).map { url =>
      ScalaestJudge.judge(url)
    }) { _ + _ } )
  }
}
