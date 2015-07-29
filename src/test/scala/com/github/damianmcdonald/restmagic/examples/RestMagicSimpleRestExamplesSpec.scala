package com.github.damianmcdonald.restmagic.examples

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes
import spray.http.HttpEntity
import spray.http.MediaTypes

class RestMagicSimpleRestExamplesSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  private val sampleJson = """{ "data": "sample" }"""

  implicit val formats = net.liftweb.json.DefaultFormats

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a json object for a GET request to path /" + rootApiPath + "/examples/simplerest/helloworld/json" in {
      Get("/" + rootApiPath + "/examples/simplerest/helloworld/json") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Hello World"
      }
    }
  }

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a xml object for a GET request to path /" + rootApiPath + "/examples/simplerest/helloworld/xml" in {
      Get("/" + rootApiPath + "/examples/simplerest/helloworld/xml") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val xml = scala.xml.XML.loadString(response)
        val value = (xml \ "response").text
        value mustEqual "Hello World"
      }
    }
  }

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a html object for a GET request to path /" + rootApiPath + "/examples/simplerest/helloworld/html" in {
      Get("/" + rootApiPath + "/examples/simplerest/helloworld/html") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val html = scala.xml.XML.loadString(response)
        val value = (html \\ "h1").text
        value mustEqual "Hello World"
      }
    }
  }

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a json object from a file for a GET request to path /" + rootApiPath + "/examples/simplerest/helloworld/file" in {
      Get("/" + rootApiPath + "/examples/simplerest/helloworld/file") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "response").extract[String]
        value mustEqual "Hello World"
      }
    }
  }

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a json object for a POST request to path /" + rootApiPath + "/examples/simplerest/post" in {
      Post("/" + rootApiPath + "/examples/simplerest/post").withEntity(HttpEntity(MediaTypes.`application/json`, sampleJson)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "status").extract[String]
        value mustEqual "success"
      }
    }
  }

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a json object for a PUT request to path /" + rootApiPath + "/examples/simplerest/put" in {
      Put("/" + rootApiPath + "/examples/simplerest/put").withEntity(HttpEntity(MediaTypes.`application/json`, sampleJson)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "status").extract[String]
        value mustEqual "success"
      }
    }
  }

  "The SimpleRestService created via RestMagicSimpleRestExamples" should {
    "return a json object for a DELETE request to path /" + rootApiPath + "/examples/simplerest/delete" in {
      Delete("/" + rootApiPath + "/examples/simplerest/delete") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "status").extract[String]
        value mustEqual "success"
      }
    }
  }

  "The SimpleRestErrorService created via RestMagicSimpleRestExamples" should {
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