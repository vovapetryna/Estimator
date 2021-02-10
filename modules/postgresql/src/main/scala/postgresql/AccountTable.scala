package postgresql

import postgresql.PostgresProfile.api._
import slick.migration.api.{Dialect, ReversibleTableMigration, TableMigration}

import scala.concurrent.ExecutionContext

class AccountTable(tag: Tag) extends Table[models.Account](tag, "account_table") {
  val id       = column[Long]("id", O.Unique, O.AutoInc)
  val login    = column[String]("login", O.Unique)
  val password = column[String]("password")
  val salt     = column[String]("salt", O.Default(""))
  val name     = column[String]("name")
  val surname  = column[String]("surname")

  val * = (id, login, password, salt, name, surname) <> (models.Account.apply _ tupled, models.Account.unapply)

  val pk = primaryKey("account_id_pk", id)
}

object AccountTable {
  val query = TableQuery[AccountTable]

  def init: DBIO[Unit] = query.schema.create

  def migrate(implicit dialect: Dialect[PostgresProfile], ec: ExecutionContext): ReversibleTableMigration[AccountTable] =
    TableMigration(TableQuery[AccountTable])
      .addColumns(_.salt)

  def addAccount(account: models.Account): DBIO[Int] = query += account

  def byLogin(login: String): Query[AccountTable, models.Account, Seq] = query.filter(_.login === login)

  def getByLogin(login: String): DBIO[Option[models.Account]] = byLogin(login).result.headOption

}
