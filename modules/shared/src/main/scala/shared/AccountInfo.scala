package shared

import upickle.default._

case class AccountInfo(login: String, password: String, name: String, surname: String)

object AccountInfo {
  implicit val rw: ReadWriter[AccountInfo] = macroRW
}
