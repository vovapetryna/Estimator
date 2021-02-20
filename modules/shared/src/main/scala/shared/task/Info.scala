package shared.task

import upickle.default._

case class Info(name: String, description: String, estimate: java.time.Duration, primaryTaskId: Option[Long])

object Info {
  import shared.implicits._
  implicit val rw: ReadWriter[Info] = macroRW
}
