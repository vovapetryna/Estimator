import com.typesafe.config.{Config, ConfigFactory}

package object conf {
  val config: Config = ConfigFactory.load().resolve()

  object errorMessages {
    object auth {
      val accountCreate: String = config.getString("errorMessages.auth.accountCreate")
    }
    object tasks {
      val create: String       = config.getString("errorMessages.tasks.create")
      val notFound: String     = config.getString("errorMessages.tasks.notFound")
      val addSecondary: String = config.getString("errorMessages.tasks.addSecondary")
      val edit: String         = config.getString("errorMessages.tasks.edit")
      val deleteTask: String   = config.getString("errorMessages.tasks.deleteTask")
    }
  }
}
