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
        db.run(postgresql.TaskTable.addTask(shared.Task.fromShared(taskInfo)))
          .successOrAPIFailureRoute(conf.errorMessages.tasks.taskCreate)
      }
  }
}
