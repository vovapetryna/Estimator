package shared.task

import upickle.default._

case class WithSecondary(task: Short, secondary: Seq[Short])

object WithSecondary {
  implicit val rw: ReadWriter[WithSecondary] = macroRW
}