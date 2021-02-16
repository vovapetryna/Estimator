import akka.event.Logging
import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server._
import akka.http.scaladsl.server.directives.DebuggingDirectives
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt

class ApiRoutes(val db: postgresql.PostgresProfile.api.Database, val config: Config)(implicit val ec: ExecutionContext)
    extends Directives
    with LazyLogging {

  import com.softwaremill.macwire._
  val endpointsRoutesInstance: EndpointRoutes = wire[EndpointRoutes]

  private implicit def myRejectionHandler: RejectionHandler =
    RejectionHandler
      .newBuilder()
      .handle {
        case MissingCookieRejection(_) =>
          complete(HttpResponse(StatusCodes.BadRequest, entity = "Request cookies are empty"))
      }
      .handle {
        case AuthorizationFailedRejection =>
          complete(HttpResponse(StatusCodes.Forbidden, entity = "Authorization Failed"))
      }
      .handleNotFound {
        complete(HttpResponse(StatusCodes.NotFound, entity = "Unimplemented route"))
      }
      .result()

  val routes: Route =
    DebuggingDirectives.logRequestResult("api", Logging.InfoLevel)(
      handleRejections(myRejectionHandler) {
        withRequestTimeout(2.minutes) {
          endpointsRoutesInstance.routes()
        }
      }
    )
}
