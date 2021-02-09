package handlers

import akka.http.scaladsl.server.{Directives, Route}
import com.typesafe.scalalogging.LazyLogging
import postgresql.PostgresProfile.api.Database

package object auth {
  class Handler(db: Database) extends api.Authentication with akkahttp.handlers.EndpointHandler with Directives with LazyLogging {
    def routes(): Route =
      login.request { credentials =>
        logger.info(credentials.toString)
        complete(api.Response.Success(true))
      }
  }
}
