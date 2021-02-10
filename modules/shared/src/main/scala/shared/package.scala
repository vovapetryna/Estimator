import upickle.default

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

package object shared {
  def nowDateTime: LocalDateTime  = LocalDateTime.now()
  val timeInfinity: LocalDateTime = LocalDateTime.MAX
  import upickle.default.readwriter
  val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
  implicit val timeRW: default.ReadWriter[LocalDateTime] =
    readwriter[String].bimap[LocalDateTime](t => t.format(timeFormatter), s => LocalDateTime.parse(s, timeFormatter))
}
