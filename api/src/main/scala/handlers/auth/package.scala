package handlers

import akka.http.scaladsl.server.{Directives, Route}
import akkahttp.directives.SessionDirectives
import com.softwaremill.session.{InMemoryRefreshTokenStorage, RefreshTokenStorage, SessionConfig, SessionManager}
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import postgresql.PostgresProfile.api.Database

import scala.concurrent.ExecutionContext

package object auth {
  class Handler(val db: Database, val config: Config)(implicit ec: ExecutionContext)
      extends api.Authentication
      with SessionDirectives
      with akkahttp.handlers.EndpointHandler
      with Directives
      with LazyLogging {

    protected val sessionConfig: SessionConfig = SessionConfig.fromConfig(config)

    protected implicit val sessionManager: SessionManager[shared.Session] =
      new SessionManager[shared.Session](sessionConfig)
    protected implicit val refreshTokenStorage: RefreshTokenStorage[shared.Session] =
      new InMemoryRefreshTokenStorage[shared.Session] {
        def log(msg: String): Unit = ()
      }

    def routes(): Route =
      login.request { credentials =>
        authenticate(credentials) { _ =>
          complete(api.Response.Success(true))
        }
      } ~ registration.request { accountInfo =>
        logger.info(accountInfo.toString)
        db.run(postgresql.AccountTable.addAccount(accountInfo)).map(_ == 1).successOrAPIFailureRoute("Failed to create account")
      }
  }
}
