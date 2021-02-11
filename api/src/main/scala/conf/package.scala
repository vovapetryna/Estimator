import com.typesafe.config.{Config, ConfigFactory}

import scala.io.{BufferedSource, Source}

package object conf {
  val config: Config = ConfigFactory.load().resolve()

  val indexPageSrc: String = {
    val indexPageSource: BufferedSource = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("app/index.html"))
    val src                             = indexPageSource.getLines.mkString
    indexPageSource.close
    src
  }

  object errorMessages {
    object auth {
      val accountCreate: String = config.getString("errorMessages.auth.accountCreate")
    }
    object tasks {
      val taskCreate: String = config.getString("errorMessages.tasks.taskCreate")
    }
  }
}
