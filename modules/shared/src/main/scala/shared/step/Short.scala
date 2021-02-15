package shared.step

import upickle.default._

case class Short(
    id: Long,
    taskId: Long,
    orderNumber: Long,
    name: String,
    description: String,
    startTime: java.time.LocalDateTime,
    endTime: java.time.LocalDateTime
)

object Short {
  import shared.implicits._
  implicit val rw: ReadWriter[Short] = macroRW
}
