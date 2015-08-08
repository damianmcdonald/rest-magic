package com.github.damianmcdonald.restmagic.configurators

import com.github.damianmcdonald.restmagic.configurators.DataMode.Inline
import org.specs2.mutable.Specification
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.http.StatusCodes._

class SimpleRestErrorConfigSpec extends Specification {

  private val jsonAuthenticateResponse = """{ "status": "authenticated" }"""
  private val jsonAuthorizeResponse = """{ "status": "authorized" }"""

  "A SimpleRestErrorConfig" should {
    "return a valid SimpleRestErrorConfig class instance using a minimal constructor" in {
      val simpleRestErrorConfig = SimpleRestErrorConfig(
        httpMethod = GET,
        apiPath = "examples",
        errorCode = InternalServerError,
        errorMessage = "Example of 500 Internal Server Error from RestMagic"
      )
      simpleRestErrorConfig must not be null
    }
  }

  "A SimpleRestErrorConfig" should {
    "return a valid SimpleRestErrorConfig class instance using a full constructor" in {
      val simpleRestErrorConfig = SimpleRestErrorConfig(
        httpMethod = GET,
        apiPath = "examples",
        errorCode = InternalServerError,
        errorMessage = "Example of 500 Internal Server Error from RestMagic",
        displayName = "Simple Rest Error example",
        displayUrl = "/examples/simplerest/error/500"
      )
      simpleRestErrorConfig must not be null
    }
  }

}