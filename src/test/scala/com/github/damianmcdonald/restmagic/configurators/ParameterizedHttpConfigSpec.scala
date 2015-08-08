package com.github.damianmcdonald.restmagic.configurators

import com.github.damianmcdonald.restmagic.configurators.DataMode.Inline
import com.github.damianmcdonald.restmagic.configurators.FormMode.ByQueryString
import com.github.damianmcdonald.restmagic.configurators.ServeMode.{ Random, Singular, ByParam }
import org.specs2.mutable.Specification
import spray.http.HttpMethods._
import spray.http.MediaTypes._

class ParameterizedHttpConfigSpec extends Specification {

  private val jsonAuthenticateResponse = """{ "status": "authenticated" }"""
  private val jsonAuthorizeResponse = """{ "status": "authorized" }"""

  "A ParameterizedHttpConfig" should {
    "return a valid ParameterizedHttpConfig class instance using a minimal constructor" in {
      val parameterizedHttpConfig = ParameterizedHttpConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/json`,
        dataMode = Inline(),
        responseData = Map(
          "english" -> """{ "response": "Hello World" }""",
          "turkish" -> """{ "response": "Merhaba Dunya" }""",
          "italian" -> """{ "response": "Salve Mondo" }""",
          "spanish" -> """{ "response": "Hola Mundo" }""",
          "german" -> """{ "response": "Halo Welt" }"""
        ),
        paramName = "lang",
        serveMode = ByParam(),
        formMode = ByQueryString()
      )
      parameterizedHttpConfig must not be null
    }
  }

  "A ParameterizedHttpConfig" should {
    "return a valid ParameterizedHttpConfig class instance using a partial constructor" in {
      val parameterizedHttpConfig = ParameterizedHttpConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/json`,
        dataMode = Inline(),
        responseData = Map(
          "english" -> """{ "response": "Hello World" }""",
          "turkish" -> """{ "response": "Merhaba Dunya" }""",
          "italian" -> """{ "response": "Salve Mondo" }""",
          "spanish" -> """{ "response": "Hola Mundo" }""",
          "german" -> """{ "response": "Halo Welt" }"""
        ),
        paramName = "lang",
        serveMode = ByParam(),
        formMode = ByQueryString(),
        validate = true
      )
      parameterizedHttpConfig must not be null
    }
  }

  "A ParameterizedHttpConfig" should {
    "return a valid ParameterizedHttpConfig class instance using a full constructor" in {
      val parameterizedHttpConfig = ParameterizedHttpConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/json`,
        dataMode = Inline(),
        responseData = Map(
          "english" -> """{ "response": "Hello World" }""",
          "turkish" -> """{ "response": "Merhaba Dunya" }""",
          "italian" -> """{ "response": "Salve Mondo" }""",
          "spanish" -> """{ "response": "Hola Mundo" }""",
          "german" -> """{ "response": "Halo Welt" }"""
        ),
        paramName = "lang",
        serveMode = ByParam(),
        formMode = ByQueryString(),
        validate = true,
        displayName = "Parameterized Http Hello World Json",
        displayUrl = "/examples/parameterizedhttp/helloworld/json?lang={{langId}}"
      )
      parameterizedHttpConfig must not be null
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig minimal constructor contains empty responseData" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map[String, String](),
          paramName = "lang",
          serveMode = ByParam(),
          formMode = ByQueryString()
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig minimal constructor is singular serve mode but responseData contains more than 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map(
            "english" -> """{ "response": "Hello World" }""",
            "turkish" -> """{ "response": "Merhaba Dunya" }""",
            "italian" -> """{ "response": "Salve Mondo" }""",
            "spanish" -> """{ "response": "Hola Mundo" }""",
            "german" -> """{ "response": "Halo Welt" }"""
          ),
          paramName = "lang",
          serveMode = Singular(),
          formMode = ByQueryString()
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig minimal constructor is not singular serve mode but responseData contains only 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map("english" -> """{ "response": "Hello World" }"""),
          paramName = "lang",
          serveMode = Random(),
          formMode = ByQueryString()
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig partial constructor contains empty responseData" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map[String, String](),
          paramName = "lang",
          serveMode = ByParam(),
          formMode = ByQueryString(),
          validate = true
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig partial constructor is singular serve mode but responseData contains more than 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map(
            "english" -> """{ "response": "Hello World" }""",
            "turkish" -> """{ "response": "Merhaba Dunya" }""",
            "italian" -> """{ "response": "Salve Mondo" }""",
            "spanish" -> """{ "response": "Hola Mundo" }""",
            "german" -> """{ "response": "Halo Welt" }"""
          ),
          paramName = "lang",
          serveMode = Singular(),
          formMode = ByQueryString(),
          validate = true
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig partial constructor is not singular serve mode but responseData contains only 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map("english" -> """{ "response": "Hello World" }"""),
          paramName = "lang",
          serveMode = Random(),
          formMode = ByQueryString(),
          validate = true
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig full constructor contains empty responseData" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map[String, String](),
          paramName = "lang",
          serveMode = ByParam(),
          formMode = ByQueryString(),
          validate = true,
          displayName = "Parameterized Http Hello World Json",
          displayUrl = "/examples/parameterizedhttp/helloworld/json?lang={{langId}}"
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig full constructor is singular serve mode but responseData contains more than 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map(
            "english" -> """{ "response": "Hello World" }""",
            "turkish" -> """{ "response": "Merhaba Dunya" }""",
            "italian" -> """{ "response": "Salve Mondo" }""",
            "spanish" -> """{ "response": "Hola Mundo" }""",
            "german" -> """{ "response": "Halo Welt" }"""
          ),
          paramName = "lang",
          serveMode = Singular(),
          formMode = ByQueryString(),
          validate = true,
          displayName = "Parameterized Http Hello World Json",
          displayUrl = "/examples/parameterizedhttp/helloworld/json?lang={{langId}}"
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

  "A ParameterizedHttpConfig" should {
    "throw an assertion error" in {
      "when a ParameterizedHttpConfig full constructor is not singular serve mode but responseData contains only 1 item" in {
        def parameterizedHttpConfig = ParameterizedHttpConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/json`,
          dataMode = Inline(),
          responseData = Map("english" -> """{ "response": "Hello World" }"""),
          paramName = "lang",
          serveMode = Random(),
          formMode = ByQueryString(),
          validate = true,
          displayName = "Parameterized Http Hello World Json",
          displayUrl = "/examples/parameterizedhttp/helloworld/json?lang={{langId}}"
        )
        parameterizedHttpConfig must throwA[AssertionError]
      }
    }
  }

}