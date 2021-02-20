package models

case class Task(
    id: Long,
    accountId: Long,
    isPrimary: Boolean,
    name: String,
    description: String,
    estimate: java.time.Duration,
) {
  lazy val pk: (Boolean, Long, Long) = (isPrimary, accountId, id)

  def toShort: shared.task.Short = shared.task.Short(id, accountId, isPrimary, name, description, estimate)

  def patchWithShort(patch: shared.task.Short): Task = copy(name = patch.name, description = patch.description)
}

object Task {
  def fromInfo(taskInfo: shared.task.Info)(implicit session: shared.Session): Task =
    Task(-1L, session.userId, taskInfo.primaryTaskId.isEmpty, taskInfo.name, taskInfo.description, taskInfo.estimate)
}
