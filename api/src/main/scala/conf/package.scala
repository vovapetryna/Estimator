import com.typesafe.config.{Config, ConfigFactory}

import scala.io.Source

package object conf {
  val config: Config = ConfigFactory.load().resolve()

  val test = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("./conf/application2.conf")).mkString

  object errorMessages {
    object auth {
      val accountCreate: String = config.getString("errorMessages.auth.accountCreate")
    }
    object tasks {
      val taskCreate: String = config.getString("errorMessages.tasks.taskCreate")
    }
  }
}
