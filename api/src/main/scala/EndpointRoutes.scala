import akka.http.scaladsl.server.{Directives, Route}
import akkahttp.directives.SessionDirectives
import com.softwaremill.session.{InMemoryRefreshTokenStorage, RefreshTokenStorage, SessionConfig, SessionManager}
import com.typesafe.config.Config
import shared.Session

import scala.concurrent.ExecutionContext

class EndpointRoutes(val db: postgresql.PostgresProfile.api.Database, val config: Config)(implicit val ec: ExecutionContext)
    extends akkahttp.handlers.EndpointHandler
    with SessionDirectives
    with Directives {
  protected val sessionConfig: SessionConfig = SessionConfig.fromConfig(config)

  protected implicit val sessionManager: SessionManager[shared.Session] =
    new SessionManager[shared.Session](sessionConfig)
  protected implicit val refreshTokenStorage: RefreshTokenStorage[shared.Session] =
    new InMemoryRefreshTokenStorage[shared.Session] {
      def log(msg: String): Unit = ()
    }

  import com.softwaremill.macwire._
  def routes(): Route =
    { implicit val session: Session = shared.Session(1, "cors", "Session", "Disabled")
      wire[handlers.tasks.Handler].routes
    } ~
      wire[handlers.auth.Handler].routes() ~ path("ping") { complete("alive") }
}
