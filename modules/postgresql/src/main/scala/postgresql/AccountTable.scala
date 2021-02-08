package postgresql

import postgresql.PostgresProfile.api._

class AccountTable(tag: Tag) extends Table[models.Account](tag, "account_table") {
  val id       = column[Long]("id", O.Unique, O.AutoInc)
  val login    = column[String]("login", O.Unique)
  val password = column[String]("password")
  val name     = column[String]("name")
  val surname  = column[String]("surname")

  val * = (id, login, password, name, surname).mapTo[models.Account]

  val pk = primaryKey("account_id_pk", id)
}

object AccountTable {
  val query = TableQuery[AccountTable]
}
