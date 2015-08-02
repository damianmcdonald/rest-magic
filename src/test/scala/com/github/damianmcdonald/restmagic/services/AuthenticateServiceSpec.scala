package com.github.damianmcdonald.restmagic.services

import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import org.specs2.mutable.Specification
import spray.http.HttpHeaders.Authorization
import spray.http._
import spray.testkit.Specs2RouteTest

class AuthenticateServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  implicit val formats = net.liftweb.json.DefaultFormats

  "The AuthenicateService created via AuthenticateTestApi" should {
    "authorize valid credentials" in {
      val validCredentials = BasicHttpCredentials("luke", "12345")
      Post("/" + rootApiPath + "/examples/logon/authorize").withHeaders(Authorization(validCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "status").extract[String]
        value mustEqual "authorized"
      }
    }
  }

  "The AuthenicateService created via AuthenticateTestApi" should {
    "reject invalid credentials" in {
      val invalidCredentials = BasicHttpCredentials("chewy", "zxcvb")
      Post("/" + rootApiPath + "/examples/logon/authorize").withHeaders(Authorization(invalidCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.Forbidden
        val response = responseAs[String]
        response must not be empty
        response === "Authorization failure!"
      }
    }
  }

  "The AuthenicateService created via AuthenticateTestApi" should {
    "authenticate valid credentials" in {
      val validCredentials = BasicHttpCredentials("han", "qwerty")
      Post("/" + rootApiPath + "/examples/logon/authenticate").withHeaders(Authorization(validCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val value = (json \ "status").extract[String]
        value mustEqual "authenticated"
      }
    }
  }

  "The AuthenicateService created via AuthenticateTestApi" should {
    "not authenticate invalid credentials" in {
      val validCredentials = BasicHttpCredentials("bad", "creds")
      Post("/" + rootApiPath + "/examples/logon/authenticate").withHeaders(Authorization(validCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.Unauthorized
        val response = responseAs[String]
        response must not be empty
        response === "Authentication failure!"
      }
    }
  }

}