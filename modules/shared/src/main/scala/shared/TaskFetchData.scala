package shared

import upickle.default._

case class TaskFetchData()

object TaskFetchData {
  implicit val rw: ReadWriter[TaskFetchData] = macroRW
}
