package com.github.damianmcdonald.restmagic.configurators

import com.github.damianmcdonald.restmagic.configurators.DataMode.Inline
import org.specs2.mutable.Specification
import spray.http.HttpMethods._
import spray.http.MediaTypes._

class SimpleRestConfigSpec extends Specification {

  private val jsonAuthenticateResponse = """{ "status": "authenticated" }"""
  private val jsonAuthorizeResponse = """{ "status": "authorized" }"""

  "A SimpleRestConfig" should {
    "return a valid SimpleRestConfig class instance using a minimal constructor" in {
      val simpleRestConfig = SimpleRestConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/json`,
        dataMode = Inline(),
        responseData = """{ "response": "Hello World" }"""
      )
      simpleRestConfig must not be null
    }
  }

  "A SimpleRestConfig" should {
    "return a valid SimpleRestConfig class instance using a partial constructor" in {
      val simpleRestConfig = SimpleRestConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/json`,
        dataMode = Inline(),
        responseData = """{ "response": "Hello World" }""",
        validate = true
      )
      simpleRestConfig must not be null
    }
  }

  "A SimpleRestConfig" should {
    "return a valid SimpleRestConfig class instance using a full constructor" in {
      val simpleRestConfig = SimpleRestConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/json`,
        dataMode = Inline(),
        responseData = """{ "response": "Hello World" }""",
        validate = true,
        displayName = "Hello World Json",
        displayUrl = "/examples/simplerest/helloworld/json"
      )
      simpleRestConfig must not be null
    }
  }

  "A SimpleRestConfig" should {
    "throw an assertion error" in {
      "when a SimpleRestConfig minimal constructor contains empty responseData" in {
        def simpleRestConfig = SimpleRestConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = ""
        )
        simpleRestConfig must throwA[AssertionError]
      }
    }
  }

  "A SimpleRestConfig" should {
    "throw an assertion error" in {
      "when a SimpleRestConfig partial constructor contains empty responseData" in {
        def simpleRestConfig = SimpleRestConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = "",
          validate = true
        )
        simpleRestConfig must throwA[AssertionError]
      }
    }
  }

  "A SimpleRestConfig" should {
    "throw an assertion error" in {
      "when a SimpleRestConfig full constructor contains empty responseData" in {
        def simpleRestConfig = SimpleRestConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = "",
          validate = true,
          displayName = "Hello World Json",
          displayUrl = "/examples/simplerest/helloworld/json"
        )
        simpleRestConfig must throwA[AssertionError]
      }
    }
  }

}