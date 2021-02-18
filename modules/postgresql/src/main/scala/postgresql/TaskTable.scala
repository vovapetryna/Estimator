package postgresql

import postgresql.PostgresProfile.api._
import slick.migration.api.Dialect

import scala.concurrent.ExecutionContext

class TaskTable(tag: Tag) extends Table[models.Task](tag, "task_table") {
  val id          = column[Long]("id", O.Unique, O.AutoInc)
  val accountId   = column[Long]("account_id")
  val name        = column[String]("name")
  val description = column[String]("description")
  val startTime   = column[java.time.LocalDateTime]("start_time")
  val endTime     = column[java.time.LocalDateTime]("end_time")

  val * = (id, accountId, name, description, startTime, endTime) <> (models.Task.apply _ tupled, models.Task.unapply)

  val accountFK = foreignKey("task_table_account_id_fk", accountId, AccountTable.query)(_.id)
  val pk        = primaryKey("task_id_pk", id)
}

object TaskTable {
  val query = TableQuery[TaskTable]

  def init: DBIO[Unit] = query.schema.create

  def migrate(implicit dialect: Dialect[PostgresProfile], ec: ExecutionContext): TableQuery[TaskTable] = query

  def addTask(task: models.Task): DBIO[models.Task] = query returning query += task

  def byId(id: Long): Query[TaskTable, models.Task, Seq]             = query.filter(_.id === id)
  def byAccount(accountId: Long): Query[TaskTable, models.Task, Seq] = query.filter(_.accountId === accountId)

  def getById(id: Long): DBIO[Option[models.Task]]          = byId(id).result.headOption
  def getByAccount(accountId: Long): DBIO[Seq[models.Task]] = byAccount(accountId).result
  def getByAccountWithSteps(accountId: Long)(implicit ec: ExecutionContext): DBIO[Seq[shared.task.WithSteps]] =
    for {
      steps <- StepTable.byTasksQ(byAccount(accountId).map(_.id)).result
      tasks <- getByAccount(accountId)
      stepsMap = steps.groupBy(_.taskId)
    } yield tasks.map(t => shared.task.WithSteps(t.toShort, stepsMap.getOrElse(t.id, Seq()).map(_.toShort)))

}
