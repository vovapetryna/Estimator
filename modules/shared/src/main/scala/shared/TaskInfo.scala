package shared

import upickle.default._

case class TaskInfo(name: String, description: String)
object TaskInfo {
  implicit val rw: ReadWriter[TaskInfo] = macroRW
}
