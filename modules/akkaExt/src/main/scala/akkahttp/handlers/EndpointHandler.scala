package akkahttp.handlers

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.server.{Directive1, Directives, Route}
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
import akkahttp.upickle.BaseUpickleSupport

trait EndpointHandler extends Directives with BaseUpickleSupport with endpoints.akkahttp.server.Endpoints {
  def jsonRequest[A](implicit evidence$1: upickle.default.ReadWriter[A]): Directive1[A] =
    Directives.entity[A](implicitly[FromRequestUnmarshaller[A]])

  def jsonResponse[A](implicit evidence$2: upickle.default.ReadWriter[A]): A => Route =
    (a: A) => Directives.complete(ToResponseMarshallable(a))

  def routes(): Route
}
