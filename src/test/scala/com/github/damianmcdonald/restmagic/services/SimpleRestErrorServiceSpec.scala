package com.github.damianmcdonald.restmagic.services

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http._
import java.io.File

class SimpleRestErrorServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  "The SimpleRestErrorService created via SimpleRestErrorTestApi" should {
    "return a 500 Internal Server Error for a GET request to path /" + rootApiPath + "/examples/simplerest/error/500" in {
      Get("/" + rootApiPath + "/examples/simplerest/error/500") ~> routes ~> check {
        status mustEqual StatusCodes.InternalServerError
        val response = responseAs[String]
        response must not be empty
        response mustEqual "Example of 500 Internal Server Error from RestMagic"
      }
    }
  }

}