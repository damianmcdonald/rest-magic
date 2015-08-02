package com.github.damianmcdonald.restmagic.services

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes
import spray.http.HttpEntity
import spray.http.MediaTypes

class ParameterizedRestErrorServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  "The ParameterizedRestErrorService created via ParameterizedRestErrorTestApi" should {
    "return a 400 Bad Request Error for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/error/1" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/error/1") ~> routes ~> check {
        status mustEqual StatusCodes.BadRequest
        val response = responseAs[String]
        response must not be empty
        response mustEqual "RestMagic BadRequest error"
      }
    }
  }

}