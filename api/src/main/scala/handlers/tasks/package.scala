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
          .successOrAPIFailureRoute(conf.errorMessages.tasks.create)
      } ~ addSecondary.request { taskInfo =>
        db.run(for {
            addedTask <- postgresql.TaskTable.addTask(models.Task.fromInfo(taskInfo))
            _         <- postgresql.TaskRelationTable.addRelation(models.TaskRelation(taskInfo.primaryTaskId.get, addedTask.id))
          } yield addedTask)
          .map(_.toShort)
          .successOrAPIFailureRoute(conf.errorMessages.tasks.addSecondary)
      } ~ edit.request { taskShort =>
        db.run(postgresql.TaskTable.update(taskShort)).map(_ == 1).successOrAPIFailureRoute(conf.errorMessages.tasks.edit)
      } ~ deleteTask.request { taskShort =>
        db.run(postgresql.TaskTable.deleteTask(taskShort))
          .map(_ == 1)
          .successOrAPIFailureRoute(conf.errorMessages.tasks.deleteTask)
      } ~ allListing.request { _ =>
        db.run(postgresql.TaskTable.getByAccountWithSecondaryGrouped(session.userId))
          .successOrAPIFailureRoute(conf.errorMessages.tasks.create)
      }
  }
}
