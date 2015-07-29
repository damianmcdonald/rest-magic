package com.github.damianmcdonald.restmagic.examples

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes
import spray.http.HttpEntity
import spray.http.MediaTypes

class RestMagicParameterizedRestExamplesSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  private val sampleJson = """{ "data": "sample" }"""

  implicit val formats = net.liftweb.json.DefaultFormats

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
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

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
    "return a html object from file for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/motu/html/file/2" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/motu/html/file/2") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val xml = scala.xml.XML.loadString(response)
        xml.isInstanceOf[scala.xml.Elem] equals true
      }
    }
  }

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
    "return a html object for a GET request to path /" + rootApiPath + "/examples/parameterizedrest/he-man/any/sample/path" in {
      Get("/" + rootApiPath + "/examples/parameterizedrest/he-man/any/sample/path") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val xml = scala.xml.XML.loadString(response)
        xml.isInstanceOf[scala.xml.Elem] equals true
      }
    }
  }

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
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

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
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

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
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

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
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

  "The ParameterizedRestService created via RestMagicParameterizedRestExamples" should {
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

  "The ParameterizedRestErrorService created via RestMagicParameterizedRestExamples" should {
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