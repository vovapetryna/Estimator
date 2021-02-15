import com.typesafe.config.{Config, ConfigFactory}

import scala.io.Source

package object conf {
  val config: Config = ConfigFactory.load().resolve()

  object errorMessages {
    object auth {
      val accountCreate: String = config.getString("errorMessages.auth.accountCreate")
    }
    object tasks {
      val taskCreate: String = config.getString("errorMessages.tasks.taskCreate")
      val notFound: String = config.getString("errorMessages.tasks.notFound")
    }
    object steps {
      val stepCreate: String = config.getString("errorMessages.steps.stepCreate")
    }
  }
}
