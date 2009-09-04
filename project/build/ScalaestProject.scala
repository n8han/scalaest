import sbt._

class ScalaestProject(info: ProjectInfo) extends ParentProject(info) {
  lazy val beginner = project("beginner", "Beginner Module")
  lazy val intermediate = project("intermediate", "Intermediate Module")
  lazy val advanced = project("advanced", "Advanced Module")
}

