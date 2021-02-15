package shared.step

import upickle.default._

case class Info(taskId: Long, orderNumber: Long, name: String, description: String)

object Info {
  implicit val rw: ReadWriter[Info] = macroRW
}
