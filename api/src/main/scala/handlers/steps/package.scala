package handlers

import akka.http.scaladsl.server.{Directives, Route}
import com.typesafe.scalalogging.LazyLogging
import postgresql.PostgresProfile.api.Database

import scala.concurrent.ExecutionContext

package object steps {
  class Handler(val db: Database)(implicit ec: ExecutionContext)
    extends api.Steps
      with akkahttp.handlers.AuthorizedEndpointHandler
      with Directives
      with LazyLogging {
    def routes(implicit session: shared.Session): Route =
      create.request { stepInfo =>
        db.run(postgresql.StepTable.addStep(models.Step.fromInfo(stepInfo)))
          .map(_.toShort)
          .successOrAPIFailureRoute(conf.errorMessages.steps.stepCreate)
      }
  }
}
