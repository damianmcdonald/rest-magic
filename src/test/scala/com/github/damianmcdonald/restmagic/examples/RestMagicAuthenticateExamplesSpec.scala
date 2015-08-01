package com.github.damianmcdonald.restmagic.examples

import com.github.damianmcdonald.restmagic.api.RestMagicApi
import org.specs2.mutable.Specification
import spray.http.HttpHeaders.Authorization
import spray.http._
import spray.testkit.Specs2RouteTest

class RestMagicAuthenticateExamplesSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  implicit val formats = net.liftweb.json.DefaultFormats

  "The AuthenicateService created via RestMagicAutehtnticateExamples" should {
    "authorize valid credentials" in {
      val validCredentials = BasicHttpCredentials("luke", "12345")
      Get("/authenticate/secured").withHeaders(Authorization(validCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        response === "Authorized successfully"
      }
    }
  }

  "The AuthenicateService created via RestMagicAuthenticateExamples" should {
    "reject invalid credentials" in {
      val invalidCredentials = BasicHttpCredentials("chewy", "12345")
      Get("/authenticate/secured").withHeaders(Authorization(invalidCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.Forbidden
        val response = responseAs[String]
        response must not be empty
        response === "Authorization failure!"
      }
    }
  }

  "The AuthenicateService created via RestMagicAuthenticateExamples" should {
    "authenticate valid credentials" in {
      val validCredentials = BasicHttpCredentials("han", "12345")
      Get("/authenticate/unsecured").withHeaders(Authorization(validCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        response === "Authenticated successfully"
      }
    }
  }

  "The AuthenicateService created via RestMagicAuthenticateExamples" should {
    "not authenticate invalid credentials" in {
      val validCredentials = BasicHttpCredentials("bad", "creds")
      Get("/authenticate/unsecured").withHeaders(Authorization(validCredentials)) ~> routes ~> check {
        status mustEqual StatusCodes.Unauthorized
        val response = responseAs[String]
        response must not be empty
        response === "Authentication failure!"
      }
    }
  }

}