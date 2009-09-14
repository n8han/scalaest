package scalaest

trait Attendance {
  /** @return meetup attendee names */
  def attendees(event_id: String, key: String): Iterable[String]
}

object ScalaestAttendance extends SolutionAttendance

class SolutionAttendance extends Attendance {
  import dispatch._
  def attendees(event_id: String, key: String) = {
    print("Querying meetup.com...")
    (new Http)(
      :/("api.meetup.com") / "rsvps.xml" <<? Map("event_id" -> event_id, "key" -> key) <> { xml =>
        println(" done.")
        for (i <- xml \\ "item" if (i \ "response").text == "yes") yield (i \ "name" text)
      }
    )
  }
}


class MockAttendance extends Attendance {
  def attendees(event_id: String, key: String) = "Dustin Whitney" :: "Nathan Hamblen" :: "Rob Eccardt" :: Nil
}
