import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import slick.migration.api.PostgresDialect

import scala.concurrent.ExecutionContextExecutor

object Main extends App with LazyLogging {
  val config = ConfigFactory.load().resolve()

  implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "api")
  implicit val ec: ExecutionContextExecutor = system.executionContext

  private val db                        = postgresql.PostgresProfile.api.Database.forConfig("postgresql", config)
  implicit val dialect: PostgresDialect = new slick.migration.api.PostgresDialect

//  initial migrations
//  db.run(postgresql.AccountTable.init).onComplete(println)
//  db.run(for {
//      _ <- postgresql.TaskTable.init
//      _ <- postgresql.TaskRelationTable.init
//      _ <- postgresql.TaskActionTable.init
//    } yield Unit)
//    .onComplete(println)
//  migrate security update
//  db.run(postgresql.AccountTable.migrate.apply()).onComplete(println)

  import com.softwaremill.macwire._
  val handlers: Route = wire[ApiRoutes].routes

  val host = config.getString("routing.rootHost")
  val port = config.getInt("routing.apiPort")

  val binding = Http().newServerAt(host, port).bind(handlers)

  binding.onComplete {
    case scala.util.Success(_) =>
      logger.info(s"=== Server is UP at http://$host:$port/ ===")
    case scala.util.Failure(ex) =>
      logger.error(s"Failed to bind to $host:$port!", ex)
      sys.exit(1)
  }

  sys.addShutdownHook {
    binding.flatMap(_.unbind()).onComplete(_ => system.terminate())
    db.close()
    logger.info("Shutdown...")
  }

}
