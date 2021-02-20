package postgresql

import postgresql.PostgresProfile.api._

class TaskActionTable(tag: Tag) extends Table[models.TaskAction](tag, "task_action_table") {
  val taskId    = column[Long]("task_id")
  val action    = column[shared.task.Action]("action")
  val timestamp = column[java.time.LocalDateTime]("timestamp")

  val * = (taskId, action, timestamp) <> (models.TaskAction.apply _ tupled, models.TaskAction.unapply)

  val pk = primaryKey("task_action_table_pk", (taskId, action, timestamp))

  val taskFK = foreignKey("task_action_table_task_table_task_id_fk", taskId, TaskTable.query)(_.id, onDelete = ForeignKeyAction.Cascade)
}

object TaskActionTable {
  val query = TableQuery[TaskActionTable]

  def init: DBIO[Unit] = query.schema.create
}
