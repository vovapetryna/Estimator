import upickle.default

import java.time.format.DateTimeFormatter
import java.time.{Duration, LocalDateTime}

package object shared {
  def nowDateTime: LocalDateTime  = LocalDateTime.now()
  val timeInfinity: LocalDateTime = LocalDateTime.MAX

  object implicits {
    import upickle.default.readwriter
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    implicit val timeRW: default.ReadWriter[LocalDateTime] =
      readwriter[String].bimap(_.format(timeFormatter), s => LocalDateTime.parse(s, timeFormatter))
    implicit val durationRW: default.ReadWriter[Duration] = readwriter[String].bimap(_.toString, Duration.parse)

  }
}
