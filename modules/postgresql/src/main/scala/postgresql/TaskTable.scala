package postgresql

import postgresql.PostgresProfile.api._

class TaskTable(tag: Tag) extends Table[models.Task](tag, "task_table") {
  val id          = column[Long]("id", O.Unique, O.AutoInc)
  val name        = column[String]("name")
  val description = column[String]("description")
  val startTime   = column[java.time.LocalDateTime]("start_time")
  val endTime     = column[java.time.LocalDateTime]("end_time")

  val * = (id, name, description, startTime, endTime).mapTo[models.Task]

  val pk = primaryKey("task_id_pk", id)
}

object TaskTable {
  val query = TableQuery[TaskTable]
}
