package com.github.damianmcdonald.restmagic.configurators

import com.github.damianmcdonald.restmagic.configurators.FormMode.ByQueryString
import com.github.damianmcdonald.restmagic.configurators.ServeMode.{ ByParam, Random, Singular }
import org.specs2.mutable.Specification
import spray.http.HttpMethods._
import spray.http.StatusCodes._

class ParameterizedHttpErrorConfigSpec extends Specification {

  private val jsonAuthenticateResponse = """{ "status": "authenticated" }"""
  private val jsonAuthorizeResponse = """{ "status": "authorized" }"""

  "A ParameterizedHttpErrorConfig" should {
    "return a valid ParameterizedHttpErrorConfig class instance using a minimal constructor" in {
      val parameterizedHttpErrorConfig = ParameterizedHttpErrorConfig(
        httpMethod = GET,
        apiPath = "examples",
        responseData = Map(
          "1" -> ErrorCode(BadRequest, "RestMagic BadRequest error"),
          "2" -> ErrorCode(BandwidthLimitExceeded, "RestMagic BandwidthLimitExceeded error"),
          "3" -> ErrorCode(GatewayTimeout, "RestMagic GatewayTimeout error"),
          "4" -> ErrorCode(NetworkConnectTimeout, "RestMagic NetworkConnectTimeout error"),
          "5" -> ErrorCode(RequestUriTooLong, "RestMagic RequestUriTooLong error")
        ),
        paramName = "errorId",
        serveMode = ByParam(),
        formMode = ByQueryString()
      )
      parameterizedHttpErrorConfig must not be null
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "return a valid ParameterizedHttpErrorConfig class instance using a full constructor" in {
      val parameterizedHttpErrorConfig = ParameterizedHttpErrorConfig(
        httpMethod = GET,
        apiPath = "examples",
        responseData = Map(
          "1" -> ErrorCode(BadRequest, "RestMagic BadRequest error"),
          "2" -> ErrorCode(BandwidthLimitExceeded, "RestMagic BandwidthLimitExceeded error"),
          "3" -> ErrorCode(GatewayTimeout, "RestMagic GatewayTimeout error"),
          "4" -> ErrorCode(NetworkConnectTimeout, "RestMagic NetworkConnectTimeout error"),
          "5" -> ErrorCode(RequestUriTooLong, "RestMagic RequestUriTooLong error")
        ),
        paramName = "errorId",
        serveMode = ByParam(),
        formMode = ByQueryString(),
        displayName = "Parameterized Http Error Get by query string",
        displayUrl = "/examples/parameterizedhttp/error/querystring/get/{{errorId}}"
      )
      parameterizedHttpErrorConfig must not be null
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpErrorConfig minimal constructor contains empty responseData" in {
        def parameterizedHttpConfig = ParameterizedHttpErrorConfig(
          httpMethod = GET,
          apiPath = "examples",
          responseData = Map[String, ErrorCode](),
          paramName = "errorId",
          serveMode = ByParam(),
          formMode = ByQueryString()
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpErrorConfig minimal constructor is singular serve mode but responseData contains more than 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpErrorConfig(
          httpMethod = GET,
          apiPath = "examples",
          responseData = Map(
            "1" -> ErrorCode(BadRequest, "RestMagic BadRequest error"),
            "2" -> ErrorCode(BandwidthLimitExceeded, "RestMagic BandwidthLimitExceeded error"),
            "3" -> ErrorCode(GatewayTimeout, "RestMagic GatewayTimeout error"),
            "4" -> ErrorCode(NetworkConnectTimeout, "RestMagic NetworkConnectTimeout error"),
            "5" -> ErrorCode(RequestUriTooLong, "RestMagic RequestUriTooLong error")
          ),
          paramName = "errorId",
          serveMode = Singular(),
          formMode = ByQueryString()
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpErrorConfig minimal constructor is not singular serve mode but responseData contains only 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpErrorConfig(
          httpMethod = GET,
          apiPath = "examples",
          responseData = Map("1" -> ErrorCode(BadRequest, "RestMagic BadRequest error")),
          paramName = "lang",
          serveMode = Random(),
          formMode = ByQueryString()
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpErrorConfig full constructor contains empty responseData" in {
        def parameterizedHttpConfig = ParameterizedHttpErrorConfig(
          httpMethod = GET,
          apiPath = "examples",
          responseData = Map[String, ErrorCode](),
          paramName = "errorId",
          serveMode = ByParam(),
          formMode = ByQueryString(),
          displayName = "Parameterized Http Error Get by query string",
          displayUrl = "/examples/parameterizedhttp/error/querystring/get/{{errorId}}"
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpErrorConfig full constructor is singular serve mode but responseData contains more than 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpErrorConfig(
          httpMethod = GET,
          apiPath = "examples",
          responseData = Map(
            "1" -> ErrorCode(BadRequest, "RestMagic BadRequest error"),
            "2" -> ErrorCode(BandwidthLimitExceeded, "RestMagic BandwidthLimitExceeded error"),
            "3" -> ErrorCode(GatewayTimeout, "RestMagic GatewayTimeout error"),
            "4" -> ErrorCode(NetworkConnectTimeout, "RestMagic NetworkConnectTimeout error"),
            "5" -> ErrorCode(RequestUriTooLong, "RestMagic RequestUriTooLong error")
          ),
          paramName = "errorId",
          serveMode = Singular(),
          formMode = ByQueryString(),
          displayName = "Parameterized Http Error Get by query string",
          displayUrl = "/examples/parameterizedhttp/error/querystring/get/{{errorId}}"
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpErrorConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpErrorConfig full constructor is not singular serve mode but responseData contains only 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpErrorConfig(
          httpMethod = GET,
          apiPath = "examples",
          responseData = Map("1" -> ErrorCode(BadRequest, "RestMagic BadRequest error")),
          paramName = "lang",
          serveMode = Random(),
          formMode = ByQueryString(),
          displayName = "Parameterized Http Error Get by query string",
          displayUrl = "/examples/parameterizedhttp/error/querystring/get/{{errorId}}"
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

}