package models

case class TaskAction(taskId: Long, action: shared.task.Action, timestamp: java.time.LocalDateTime)
