package shared.task

import upickle.default._

case class WithSteps(task: Short, steps: Seq[shared.step.Short])

object WithSteps {
  implicit val rw: ReadWriter[WithSteps] = macroRW
}