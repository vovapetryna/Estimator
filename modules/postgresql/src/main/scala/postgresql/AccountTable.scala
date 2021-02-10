package postgresql

import models.Account
import postgresql.PostgresProfile.api._

class AccountTable(tag: Tag) extends Table[models.Account](tag, "account_table") {
  val id       = column[Long]("id", O.Unique, O.AutoInc)
  val login    = column[String]("login", O.Unique)
  val password = column[String]("password")
  val name     = column[String]("name")
  val surname  = column[String]("surname")

  val * = (id, login, password, name, surname) <> (models.Account.apply _ tupled, models.Account.unapply)

  val pk = primaryKey("account_id_pk", id)
}

object AccountTable {
  val query = TableQuery[AccountTable]

  def init: DBIO[Unit] = query.schema.create

  def addAccount(accountInfo: shared.AccountInfo): DBIO[Int] = query += models.Account.fromShared(accountInfo)

  def byLogin(login: String): Query[AccountTable, Account, Seq] = query.filter(_.login === login)

  def getByLogin(login: String): DBIO[Option[models.Account]] = byLogin(login).result.headOption

}
