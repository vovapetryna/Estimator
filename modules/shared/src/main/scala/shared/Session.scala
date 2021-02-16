package shared

import upickle.default._

case class Session(userId: Long, login: String, name: String, surname: String)

object Session {
  implicit val rw: ReadWriter[Session] = macroRW
}
