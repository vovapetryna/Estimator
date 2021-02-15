package shared.account

import upickle.default._

case class Info(login: String, password: String, name: String, surname: String)

object Info {
  implicit val rw: ReadWriter[Info] = macroRW
}
