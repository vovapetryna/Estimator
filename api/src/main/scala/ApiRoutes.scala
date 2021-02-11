import akka.http.scaladsl.model.Uri.Path
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}
import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext

class ApiRoutes(val db: postgresql.PostgresProfile.api.Database, val config: Config)(implicit val ec: ExecutionContext)
    extends Directives
    with LazyLogging {

  import com.softwaremill.macwire._
  val endpointsRoutesInstance: EndpointRoutes = wire[EndpointRoutes]
  private val indexPage                       = HttpEntity(ContentTypes.`text/html(UTF-8)`, Index())

  val routes: Route =
    endpointsRoutesInstance.routes ~
      pathSingleSlash {
        get {
          complete {
            indexPage
          }
        }
      } ~ path(RemainingPath) {
      case p if p.startsWith(Path("api")) => reject
      case _ =>
        get {
          complete {
            indexPage
          }
        }
    }
}
