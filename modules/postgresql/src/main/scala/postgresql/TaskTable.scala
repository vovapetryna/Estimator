package postgresql

import postgresql.PostgresProfile.api._
import slick.migration.api.Dialect

import scala.concurrent.ExecutionContext

class TaskTable(tag: Tag) extends Table[shared.Task](tag, "task_table") {
  val id          = column[Long]("id", O.Unique, O.AutoInc)
  val name        = column[String]("name")
  val description = column[String]("description")
  val startTime   = column[java.time.LocalDateTime]("start_time")
  val endTime     = column[java.time.LocalDateTime]("end_time")

  val * = (id, name, description, startTime, endTime) <> (shared.Task.apply _ tupled, shared.Task.unapply)

  val pk = primaryKey("task_id_pk", id)
}

object TaskTable {
  val query = TableQuery[TaskTable]

  def init: DBIO[Unit] = query.schema.create

  def migrate(implicit dialect: Dialect[PostgresProfile], ec: ExecutionContext): TableQuery[TaskTable] = query

  def addTask(task: shared.Task): DBIO[shared.Task] = query returning query += task

  def byId(id: Long): Query[TaskTable, shared.Task, Seq] = query.filter(_.id === id)

  def getById(id: Long): DBIO[Option[shared.Task]] = byId(id).result.headOption
}
