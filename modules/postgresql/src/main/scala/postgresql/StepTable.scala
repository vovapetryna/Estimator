package postgresql

import postgresql.PostgresProfile.api._
import slick.migration.api.Dialect

import scala.concurrent.ExecutionContext

class StepTable(tag: Tag) extends Table[models.Step](tag, "step_table") {
  val id          = column[Long]("id", O.Unique, O.AutoInc)
  val taskId      = column[Long]("task_id")
  val orderNumber = column[Long]("order_number")
  val name        = column[String]("name")
  val description = column[String]("description")
  val startTime   = column[java.time.LocalDateTime]("start_time")
  val endTime     = column[java.time.LocalDateTime]("end_time")

  val * = (id, taskId, orderNumber, name, description, startTime, endTime) <> (models.Step.apply _ tupled, models.Step.unapply)

  val taskFk = foreignKey("step_table_task_id_fk", taskId, TaskTable.query)(_.id)
  val pk     = primaryKey("step_id_pk", id)
}

object StepTable {
  val query = TableQuery[StepTable]

  def init: DBIO[Unit] = query.schema.create

  def migrate(implicit dialect: Dialect[PostgresProfile], ec: ExecutionContext): TableQuery[StepTable] = query

  def addStep(step: models.Step): DBIO[models.Step] = query returning query += step

  def byId(id: Long): Query[StepTable, models.Step, Seq]                                 = query.filter(_.id === id)
  def byTask(taskId: Long): Query[StepTable, models.Step, Seq]                           = query.filter(_.taskId === taskId)
  def byTasksQ(taskIds: Query[Rep[Long], Long, Seq]): Query[StepTable, models.Step, Seq] = query.filter(_.taskId in taskIds)

  def getById(id: Long): DBIO[Option[models.Step]]    = byId(id).result.headOption
  def getByTask(taskId: Long): DBIO[Seq[models.Step]] = byTask(taskId).result

}
