package models

case class Task(
    id: Long,
    accountId: Long,
    name: String,
    description: String,
    startTime: java.time.LocalDateTime,
    endTime: java.time.LocalDateTime
) {
  def toShort: shared.task.Short = shared.task.Short(id, accountId, name, description, startTime, endTime)
}

object Task {
  def fromInfo(taskInfo: shared.task.Info)(implicit session: shared.Session): Task =
    Task(-1L, session.userId, taskInfo.name, taskInfo.description, shared.nowDateTime, shared.nowDateTime.plusHours(1))
}
