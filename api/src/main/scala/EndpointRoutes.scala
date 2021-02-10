import akka.http.scaladsl.server.{Directives, Route}
import com.typesafe.config.Config

import scala.concurrent.ExecutionContext

class EndpointRoutes(val db: postgresql.PostgresProfile.api.Database, val config: Config)(implicit val ec: ExecutionContext)
    extends akkahttp.handlers.EndpointHandler
    with Directives {
  import com.softwaremill.macwire._

  def routes(): Route =
    wire[handlers.auth.Handler].routes()
}
