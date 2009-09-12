package scalaest

object App {
  def main(args: Array[String]) {
    args match {
      case Seq(event_id, key) => 
        ScalaestSorter.sort(
          ScalaestJudge.judge(ScalaestAttendance.attendees(event_id, key))
        ).foreach(println)
      case _ => 
        println("----------- Usage: run <event id> <api key> -----------")
    }
  }
}
