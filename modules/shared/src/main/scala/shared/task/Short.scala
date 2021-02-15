package shared.task

import upickle.default._

case class Short(
    id: Long,
    accountId: Long,
    name: String,
    description: String,
    startTime: java.time.LocalDateTime,
    endTime: java.time.LocalDateTime
)

object Short {
  import shared.implicits._
  implicit val rw: ReadWriter[Short] = macroRW
}
