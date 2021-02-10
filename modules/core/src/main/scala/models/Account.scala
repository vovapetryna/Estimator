package models

case class Account(id: Long, login: String, password: String, salt: String, name: String, surname: String) {
  def session: shared.Session = shared.Session(id, login)
}

object Account {
  def fromShared(accountInfo: shared.AccountInfo)(hash: String, salt: String): Account =
    Account(-1L, accountInfo.login, hash, salt, accountInfo.name, accountInfo.surname)
}
