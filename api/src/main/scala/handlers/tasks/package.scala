package handlers

import akka.http.scaladsl.server.{Directives, Route}
import com.typesafe.scalalogging.LazyLogging
import postgresql.PostgresProfile.api.Database

import scala.concurrent.ExecutionContext

package object tasks {
  class Handler(val db: Database)(implicit ec: ExecutionContext)
      extends api.Tasks
      with akkahttp.handlers.AuthorizedEndpointHandler
      with Directives
      with LazyLogging {
    def routes(implicit session: shared.Session): Route =
      create.request { taskInfo =>
        db.run(postgresql.TaskTable.addTask(models.Task.fromInfo(taskInfo)))
          .map(_.toShort)
          .map(t => shared.task.WithSteps(t, Seq()))
          .successOrAPIFailureRoute(conf.errorMessages.tasks.taskCreate)
      } ~ allListing.request { _ =>
        db.run(postgresql.TaskTable.getByAccountWithSteps(session.userId))
          .successOrAPIFailureRoute(conf.errorMessages.tasks.taskCreate)
      }
  }
}
