import akka.http.scaladsl.server.{Directives, Route}

class EndpointRoutes(val db: postgresql.PostgresProfile.api.Database) extends akkahttp.handlers.EndpointHandler with Directives{
  import com.softwaremill.macwire._

  def routes(): Route =
    wire[handlers.auth.Handler].routes()
}
