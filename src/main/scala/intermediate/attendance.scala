package scalaest

trait Attendance {
  def attendees(event_id: String, key: String): Iterable[String]
}

object ScalaestAttendance extends MockAttendance

class MockAttendance extends Attendance {
  def attendees(event_id: String, key: String) = "Dustin Whitney" :: "Nathan Hamblen" :: "Rob Eccardt" :: Nil
}
