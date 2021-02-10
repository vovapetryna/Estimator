package models

case class Account(id: Long, login: String, password: String, name: String, surname: String) {
  def session: shared.Session = shared.Session(id, login)
}

object Account {
  def fromShared(accountInfo: shared.AccountInfo): Account =
    Account(-1L, accountInfo.login, accountInfo.password, accountInfo.name, accountInfo.surname)
}
