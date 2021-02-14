package shared

import upickle.default._

case class Task(id: Long, name: String, description: String, startTime: java.time.LocalDateTime, endTime: java.time.LocalDateTime)
object Task {
  def fromShared(taskInfo: shared.TaskInfo): Task =
    Task(-1L, taskInfo.name, taskInfo.description, shared.nowDateTime, shared.nowDateTime.plusHours(1))
  implicit val rw: ReadWriter[Task] = macroRW
}
