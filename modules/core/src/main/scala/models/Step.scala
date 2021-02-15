package models

case class Step(
    id: Long,
    taskId: Long,
    orderNumber: Long,
    name: String,
    description: String,
    startTime: java.time.LocalDateTime,
    endTime: java.time.LocalDateTime
) {
  def toShort: shared.step.Short = shared.step.Short(id, taskId, orderNumber, name, description, startTime, endTime)
}

object Step {
  def fromInfo(stepInfo: shared.step.Info): Step =
    Step(
      -1L,
      stepInfo.taskId,
      stepInfo.orderNumber,
      stepInfo.name,
      stepInfo.description,
      shared.nowDateTime,
      shared.nowDateTime.plusHours(1)
    )
}
