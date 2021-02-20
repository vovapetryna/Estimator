package shared

import upickle.default._

package object task {
  sealed abstract class Action(represent: String)
  object Action {
    case object Start  extends Action("start")
    case object Stop   extends Action("stop")
    case object Finish extends Action("finish")

    implicit val rw: ReadWriter[Action] = macroRW
  }
}
