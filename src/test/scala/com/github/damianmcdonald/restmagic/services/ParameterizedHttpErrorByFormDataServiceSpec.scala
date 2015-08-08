package com.github.damianmcdonald.restmagic.services

import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import org.specs2.mutable.Specification
import spray.http._
import spray.testkit.Specs2RouteTest

class ParameterizedHttpErrorByFormDataServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  "The ParameterizedHttpErrorService created via ParameterizedHttpErrorByFormDataTestApi" should {
    "return a 400 Bad Request Error for a POST request to path /" + rootApiPath + "/examples/parameterizedhttp/error/formdata/post" in {
      val data = Map("errorId" -> "2")
      Post("/" + rootApiPath + "/examples/parameterizedhttp/error/formdata/post", FormData(data)) ~> routes ~> check {
        status mustEqual StatusCodes.BandwidthLimitExceeded
        val response = responseAs[String]
        response must not be empty
        response mustEqual "RestMagic BandwidthLimitExceeded error"
      }
    }
  }

}