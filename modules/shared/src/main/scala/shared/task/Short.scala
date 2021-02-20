package shared.task

import upickle.default._

case class Short(
    id: Long,
    accountId: Long,
    isPrimary: Boolean,
    name: String,
    description: String,
    estimate: java.time.Duration,
) {
  lazy val pk: (Boolean, Long, Long) = (isPrimary, accountId, id)
}

object Short {
  import shared.implicits._
  implicit val rw: ReadWriter[Short] = macroRW
}
