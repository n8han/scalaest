package scalaest

object App {
  def main(args: Array[String]) {
    ScalaestJudge.judge(ScalaestAttendance.attendees).foreach(println)
  }
}
