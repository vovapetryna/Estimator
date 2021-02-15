package shared.task

import upickle.default._

case class Info(name: String, description: String)

object Info {
  implicit val rw: ReadWriter[Info] = macroRW
}
