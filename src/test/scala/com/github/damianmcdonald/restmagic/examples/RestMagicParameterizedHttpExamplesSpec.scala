package com.github.damianmcdonald.restmagic.examples

import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import org.specs2.mutable.Specification
import spray.http._
import spray.testkit.Specs2RouteTest

class RestMagicParameterizedHttpExamplesSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  private val sampleJson = """{ "lang": "2" }"""

  implicit val formats = net.liftweb.json.DefaultFormats

  "The ParameterizedHttpService created via RestMagicParameterizedHtpExamples" should {
    "return a json object for a GET request to path /" + rootApiPath + "/examples/parameterizedhttp/helloworld/json?lang=turkish" in {
      Get("/" + rootApiPath + "/examples/parameterizedhttp/helloworld/json?lang=turkish") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedHttpService created via RestMagicParameterizedHttpExamples" should {
    "return a json object for a POST request to path /" + rootApiPath + "/examples/parameterizedhttp/post" in {
      val data = Map("lang" -> "2")
      Post("/" + rootApiPath + "/examples/parameterizedhttp/post", FormData(data)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedHttpService created via RestMagicParameterizedHttpExamples" should {
    "return a json object for a PUT request to path /" + rootApiPath + "/examples/parameterizedhttp/put" in {
      val data = Map("lang" -> "2")
      Put("/" + rootApiPath + "/examples/parameterizedhttp/put", FormData(data)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedHttpService created via RestMagicParameterizedHttpExamples" should {
    "return a json object for a DELETE request to path /" + rootApiPath + "/examples/parameterizedhttp/delete" in {
      val data = Map("lang" -> "2")
      Delete("/" + rootApiPath + "/examples/parameterizedhttp/delete", FormData(data)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedHttpErrorService created via RestMagicParameterizedHttpExamples" should {
    "return a 400 Bad Request Error for a GET request to path /" + rootApiPath + "/examples/parameterizedhttp/error/querystring/get?errorId=1" in {
      Get("/" + rootApiPath + "/examples/parameterizedhttp/error/querystring/get?errorId=1") ~> routes ~> check {
        status mustEqual StatusCodes.BadRequest
        val response = responseAs[String]
        response must not be empty
        response mustEqual "RestMagic BadRequest error"
      }
    }
  }

  "The ParameterizedHttpErrorService created via RestMagicParameterizedHttpExamples" should {
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