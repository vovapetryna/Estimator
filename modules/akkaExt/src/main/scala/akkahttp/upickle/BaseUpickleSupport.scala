package akkahttp.upickle

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model.{ContentTypeRange, HttpEntity}
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}
import akka.util.ByteString
import upickle.default._

import scala.collection.immutable.Seq

object BaseUpickleSupport extends BaseUpickleSupport

trait BaseUpickleSupport {

  def unmarshallerContentTypes: Seq[ContentTypeRange] =
    List(`application/json`)

  implicit final def marshaller[A](implicit rw: ReadWriter[A]): ToEntityMarshaller[A] =
    Marshaller.withFixedContentType(`application/json`) { json =>
      HttpEntity(`application/json`, write[A](json))
    }

  implicit def unmarshaller[A](implicit rw: ReadWriter[A]): FromEntityUnmarshaller[A] =
    Unmarshaller.byteStringUnmarshaller
      .forContentTypes(unmarshallerContentTypes: _*)
      .map {
        case ByteString.empty => throw Unmarshaller.NoContentException
        case data             => read[A](data.asByteBuffer)
      }
}
