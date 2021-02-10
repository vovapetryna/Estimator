package akkahttp.directives

import akka.http.scaladsl.server._
import akkahttp.directives.SessionDirectives.WrongLoginOrPassword
import com.softwaremill.session.SessionDirectives.{invalidateSession, requiredSession, setSession}
import com.softwaremill.session.SessionOptions._
import com.softwaremill.session._
import com.typesafe.scalalogging.LazyLogging
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

trait SessionDirectives extends Directives with LazyLogging {
  val db: postgresql.PostgresProfile.api.Database

  protected implicit def serializer(implicit rw: ReadWriter[shared.Session]): SessionSerializer[shared.Session, String] =
    new SingleValueSessionSerializer[shared.Session, String](s => write(s), s => Try(read[shared.Session](s)))

  protected implicit val sessionManager: SessionManager[shared.Session]
  protected implicit val refreshTokenStorage: RefreshTokenStorage[shared.Session]

  lazy val authRequired: Directive1[shared.Session] = requiredSession(refreshable, usingCookies)
  lazy val myInvalidateSession: Directive0          = invalidateSession(refreshable, usingCookies)

  def authenticate(credentials: shared.AccountCredentials): Directive1[shared.Session] =
    onSuccess(db.run(postgresql.AccountTable.getByLogin(credentials.login))).flatMap {
      case Some(account) if account.password == credentials.password =>
        val session = account.session
        setSession(refreshable, usingCookies, session).tflatMap { _ =>
          provide(session)
        }
      case _ => reject(WrongLoginOrPassword)
    }
}

object SessionDirectives {
  case object WrongLoginOrPassword extends Rejection
}
