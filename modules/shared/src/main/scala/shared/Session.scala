package shared

import upickle.default._

case class Session(userId: Long, login: String)

object Session {
  implicit val rw: ReadWriter[Session] = macroRW
}
