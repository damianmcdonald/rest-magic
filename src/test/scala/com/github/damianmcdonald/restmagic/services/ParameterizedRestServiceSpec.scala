package com.github.damianmcdonald.restmagic.services

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes
import spray.http.HttpEntity
import spray.http.MediaTypes

class ParameterizedRestServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  private val sampleJson = """{ "data": "sample" }"""

  implicit val formats = net.liftweb.json.DefaultFormats

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/helloworld/json/3" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/helloworld/json/turkish") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object from file for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/motu/json/file/2" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/motu/json/file/2") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "name").extract[String]
        value mustEqual "Skeletor"
      }
    }
  }

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/matchany/any/sample/path" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/matchany/any/sample/path") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        val xs = List("Hello World", "Merhaba Dunya", "Salve Mondo", "Hola Mundo", "Halo Welt")
        xs must contain(value)
      }
    }
  }

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/matchpath/asparam/turkish/hello/world/response" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/matchpath/asparam/turkish/hello/world/response") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object for a POST request to path /" + rootApiPath + "/examples/parameterizedrest/post/2" in {
      Post("/" + rootApiPath + "/examples/parameterizedrest/post/2").withEntity(HttpEntity(MediaTypes.`application/json`, sampleJson)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object for a PUT request to path /" + rootApiPath + "/examples/parameterizedrest/put/2" in {
      Put("/" + rootApiPath + "/examples/parameterizedrest/put/2").withEntity(HttpEntity(MediaTypes.`application/json`, sampleJson)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

  "The ParameterizedRestService created via ParameterizedRestTestApi" should {
    "return a json object for a DELETE request to path /" + rootApiPath + "/examples/parameterizedrest/delete/2" in {
      Delete("/" + rootApiPath + "/examples/parameterizedrest/delete/2") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Merhaba Dunya"
      }
    }
  }

}