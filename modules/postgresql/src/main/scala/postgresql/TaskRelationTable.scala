package postgresql

import postgresql.PostgresProfile.api._

class TaskRelationTable(tag: Tag) extends Table[models.TaskRelation](tag, "task_relation_table") {
  val primaryId   = column[Long]("primary_id")
  val secondaryId = column[Long]("secondary_id")

  val * = (primaryId, secondaryId) <> (models.TaskRelation.apply _ tupled, models.TaskRelation.unapply)

  val primaryFK =
    foreignKey("task_relation_table_primary_task_id_fk", primaryId, TaskTable.query)(_.id, onDelete = ForeignKeyAction.Restrict)
  val secondaryFK =
    foreignKey("task_relation_table_secondary_task_id_fk", secondaryId, TaskTable.query)(_.id, onDelete = ForeignKeyAction.Cascade)
  val pk = primaryKey("task_relation_table_id_pk", (primaryId, secondaryId))
}

object TaskRelationTable {
  val query = TableQuery[TaskRelationTable]

  def init: DBIO[Unit] = query.schema.create

  def addRelation(relation: models.TaskRelation): DBIO[Int] = query += relation
}
