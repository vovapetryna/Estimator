package shared.task

import upickle.default._

case class FetchData()

object FetchData {
  implicit val rw: ReadWriter[FetchData] = macroRW
}
