package akkahttp.utils

import de.mkammerer.argon2.Argon2Factory.Argon2Types
import de.mkammerer.argon2.{Argon2, Argon2Factory}

class PasswordHashing {
  private val argon2: Argon2 = Argon2Factory.create(Argon2Types.ARGON2d)

  def hashPassword(password: String, salt: String): String =
    argon2.hash(2, 16383, 4, (salt + password).toCharArray)

  def verifyPassword(hash: String, password: String, salt: String): Boolean =
    argon2.verify(hash, (salt + password).toCharArray)
}
