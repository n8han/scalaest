import sbt._

class ScalaestProject(info: ProjectInfo) extends DefaultProject(info) {
  val dispatch = "net.databinder" %% "dispatch-http" % "0.6.0-SNAPSHOT"
  override def compileOptions = super.compileOptions ++ Seq(Unchecked)
}

