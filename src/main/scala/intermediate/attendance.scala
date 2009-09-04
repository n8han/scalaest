package scalaest

trait Attendance {
  def attendees: Iterable[String]
}

object ScalaestAttendance extends MockAttendance

class MockAttendance extends Attendance {
  def attendees = "Dustin Whitney" :: "Nathan Hamblen" :: "Rob Eccardt" :: Nil
}
