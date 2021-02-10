import com.typesafe.config.{Config, ConfigFactory}

package object conf {
  val config: Config = ConfigFactory.load().resolve()

  object errorMessages {
    object auth {
      val accountCreate: String = config.getString("errorMessages.auth.accountCreate")
    }
    object tasks {
      val taskCreate: String = config.getString("errorMessages.tasks.taskCreate")
    }
  }
}
