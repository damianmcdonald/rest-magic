package com.github.damianmcdonald.restmagic.services

import com.github.damianmcdonald.restmagic.api.RestMagicApi
import org.specs2.mutable.Specification
import spray.http._
import spray.testkit.Specs2RouteTest

class FileDownloadServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  implicit val formats = net.liftweb.json.DefaultFormats

  "The FileDownloadService created via FileDownloadTestApi" should {
    "return a binary pdf file as attachment" in {
      Get("/" + rootApiPath + "/examples/filedownload/attachment") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
      }
    }
  }

  "The FileDownloadService created via FileDownloadTestApi" should {
    "return a binary pdf file inline" in {
      Get("/" + rootApiPath + "/examples/filedownload/inline") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
      }
    }
  }

}