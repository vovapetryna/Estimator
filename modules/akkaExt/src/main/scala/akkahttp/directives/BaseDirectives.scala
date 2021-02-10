package akkahttp.directives

import akka.http.scaladsl.server.{Directives, Route}
import akkahttp.upickle.BaseUpickleSupport
import com.typesafe.scalalogging.LazyLogging
import upickle.default

import scala.concurrent.{ExecutionContext, Future}

trait BaseDirectives extends Directives with LazyLogging with BaseUpickleSupport {
  implicit class ExceptionOps[T](future: Future[api.Response[T]]) {
    def orAPIFailure(error: String)(implicit ec: ExecutionContext): Future[api.Response[T]] = future.recover {
      case ex =>
        logger.error(s"Request failed: $error", ex)
        api.Response.Failure(error + ": " + ex.getMessage)
    }

    def orAPIFailureRoute(error: String)(implicit ec: ExecutionContext, rw: default.ReadWriter[T]): Route =
      onSuccess(orAPIFailure(error)) { x =>
        complete(x)
      }
  }

  implicit class ResponseOps[T](future: Future[T]) {
    def successOrAPIFailure(error: String)(implicit ec: ExecutionContext): Future[api.Response[T]] =
      future.map(api.Response.Success.apply).orAPIFailure(error)

    def successOrAPIFailureRoute(error: String)(implicit ec: ExecutionContext, rw: default.ReadWriter[T]): Route =
      onSuccess(successOrAPIFailure(error)) { x =>
        complete(x)
      }
  }
}
