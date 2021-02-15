package shared.account

import upickle.default._

case class Credentials(login: String, password: String)

object Credentials {
  implicit val rw: ReadWriter[Credentials] = macroRW
}
