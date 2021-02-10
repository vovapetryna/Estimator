package akkahttp.utils

import java.security.SecureRandom

object Salt {
  private val secureRandom = new SecureRandom()
  def generate(length: Int = 16, bound: Int = 93, range: Int = 33): String = {
    val sb = new StringBuffer()
    for (_ <- 1 to length) {
      sb.append((secureRandom.nextInt(bound) + range).toChar)
    }
    sb.toString
  }
}
