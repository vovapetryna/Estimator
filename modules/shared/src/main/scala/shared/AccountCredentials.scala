package shared

import upickle.default._

case class AccountCredentials(login: String, password: String)

object AccountCredentials {
  implicit val rw: ReadWriter[AccountCredentials] = macroRW
}
