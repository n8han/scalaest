package scalaest

trait Attendance {
  def attendees(event_id: String, key: String): Iterable[String]
}

object ScalaestAttendance extends SolutionAttendance

class SolutionAttendance extends Attendance {
  import scala.xml.XML
  def attendees(event_id: String, key: String) = {
    println("Querying meetup.com...")
    for (
      i <- XML.load(
        "http://api.meetup.com/rsvps.xml?event_id=%s&key=%s" format (event_id, key)
      ) \\ "item" if (i \ "response").text == "yes"
    ) yield i \ "name" text
  }
}


class MockAttendance extends Attendance {
  def attendees(event_id: String, key: String) = "Dustin Whitney" :: "Nathan Hamblen" :: "Rob Eccardt" :: Nil
}
