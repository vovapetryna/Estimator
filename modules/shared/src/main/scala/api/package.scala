import upickle.default._

import scala.annotation.implicitNotFound

package object api {
  trait API extends endpoints.algebra.Endpoints with endpoints.algebra.JsonEntities {
    protected val root: Path[Unit]
    protected val apiPath: Path[Unit] = path / "api" / "v1"

    @implicitNotFound(
      "JsonResponse: Not found ReadWriter for ${A}. Ensure ReadWriter is defined in ${A} companion object or imported into this scope"
    )
    type JsonResponse[A] = ReadWriter[A]

    @implicitNotFound(
      "JsonRequest: Not found ReadWriter for ${A}. Ensure ReadWriter is defined in ${A} companion object or imported into this scope"
    )
    type JsonRequest[A] = ReadWriter[A]

    def postEndpoint(
        e: TypedEndpoint,
        url: Path[Unit]
    )(implicit ev1: JsonRequest[e.Request], ev2: JsonResponse[api.Response[e.Response]]): Endpoint[e.Request, api.Response[e.Response]] =
      endpoint(post[Unit, e.Request, Unit, e.Request](url, jsonRequest[e.Request]), jsonResponse[api.Response[e.Response]])
  }

  sealed trait Response[+T] {
    val isOk: Boolean
    def map[B](f: T => B): Response[B]
  }

  object Response {

    case class Success[+T](success: T) extends Response[T] {
      val isOk: Boolean                  = true
      def map[B](f: T => B): Response[B] = Success(f(success))
    }
    object Success {
      implicit def rw[T](implicit rwT: ReadWriter[T]): ReadWriter[Success[T]] = macroRW[Success[T]]
    }

    case class Failure(error: String, isOk: Boolean = false) extends Response[Nothing] {
      def map[B](f: Nothing => B): Response[B] = Failure(error)
    }
    object Failure {
      implicit val rw: ReadWriter[Failure] = macroRW[Failure]
    }

    implicit def rw[T](implicit rwT: ReadWriter[T]): ReadWriter[Response[T]] = ReadWriter.merge(Success.rw[T], Failure.rw)
  }

  trait TypedEndpoint {
    type Request
    type Response
  }
}
