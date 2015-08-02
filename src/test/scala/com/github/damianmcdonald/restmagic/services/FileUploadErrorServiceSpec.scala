package com.github.damianmcdonald.restmagic.services

import java.nio.file.{ Paths, Path }

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http._
import java.io.File

class FileUploadErrorServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  implicit val formats = net.liftweb.json.DefaultFormats

  "The FileUploadErrorService created via FileUploadErrorTestApi" should {
    "reject a multi-part form data upload containing a binary file" in {
      val resourceUrl = this.getClass().getResource("/upload-test.jpg")
      val resourcePath: Path = Paths.get(resourceUrl.toURI());
      val payload = MultipartFormData(Seq(BodyPart(resourcePath.toFile, "myfile", MediaTypes.`image/jpeg`)))
      Post("/" + rootApiPath + "/examples/fileupload/error/post", payload) ~> routes ~> check {
        status mustEqual StatusCodes.InternalServerError
        val response = responseAs[String]
        response must not be empty
        response mustEqual "File upload has failed!"
      }
    }
  }

}