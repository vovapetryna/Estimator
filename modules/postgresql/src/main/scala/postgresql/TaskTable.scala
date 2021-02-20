package postgresql

import postgresql.PostgresProfile.api._
import slick.migration.api.Dialect

import scala.concurrent.ExecutionContext

class TaskTable(tag: Tag) extends Table[models.Task](tag, "task_table") {
  val id          = column[Long]("id", O.Unique, O.AutoInc)
  val accountId   = column[Long]("account_id")
  val isPrimary   = column[Boolean]("is_primary")
  val name        = column[String]("name")
  val description = column[String]("description")
  val estimate    = column[java.time.Duration]("estimate")

  val * = (id, accountId, isPrimary, name, description, estimate) <> (models.Task.apply _ tupled, models.Task.unapply)

  val accountFK = foreignKey("task_table_account_id_fk", accountId, AccountTable.query)(_.id)
  val pk        = primaryKey("task_id_pk", (isPrimary, accountId, id))
}

object TaskTable {
  val query = TableQuery[TaskTable]

  def init: DBIO[Unit] = query.schema.create

  def migrate(implicit dialect: Dialect[PostgresProfile], ec: ExecutionContext): TableQuery[TaskTable] = query

  def addTask(task: models.Task): DBIO[models.Task] = query returning query += task

  def byPk(valuePk: (Boolean, Long, Long)): Query[TaskTable, models.Task, Seq] =
    query.filter(t => t.isPrimary === valuePk._1 && t.accountId === valuePk._2 && t.id === valuePk._3)
  def byAccountWithSecondary(accountId: Long): Query[(TaskTable, Rep[Option[TaskTable]]), (models.Task, Option[models.Task]), Seq] =
    for {
      taskPair <- query
        .filter(t => t.isPrimary && t.accountId === accountId)
        .joinLeft(TaskRelationTable.query)
        .on(_.id === _.primaryId)
        .joinLeft(query.filter(t => !t.isPrimary && t.accountId === accountId))
        .on(_._2.map(_.secondaryId) === _.id)
        .map { case ((p, _), s) => p -> s }
    } yield taskPair

  def getByAccountWithSecondary(accountId: Long): DBIO[Seq[(models.Task, Option[models.Task])]] = byAccountWithSecondary(accountId).result
  def getByAccountWithSecondaryGrouped(accountId: Long)(implicit ec: ExecutionContext): DBIO[Seq[shared.task.WithSecondary]] =
    byAccountWithSecondary(accountId).result.map(_.groupBy(_._1).map {
      case (t, s) => shared.task.WithSecondary(t.toShort, s.flatMap(_._2.toSeq).map(_.toShort))
    }.toSeq)

  def update(newTask: shared.task.Short)(implicit ec: ExecutionContext): DBIO[Int] =
    for {
      Some(oldTask) <- byPk(newTask.pk).result.headOption
      updated       <- byPk(newTask.pk).update(oldTask.patchWithShort(newTask))
    } yield updated

  def deleteTask(taskForDelete: shared.task.Short): DBIO[Int] = byPk(taskForDelete.pk).delete
}
