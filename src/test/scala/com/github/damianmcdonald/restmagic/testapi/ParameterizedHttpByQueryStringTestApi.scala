package com.github.damianmcdonald.restmagic.testapi

import com.github.damianmcdonald.restmagic.configurators.FormMode.{ ByFormData, ByQueryString }
import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.configurators.DataMode._
import com.github.damianmcdonald.restmagic.configurators.ServeMode._
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.Directives._
import spray.http.StatusCodes._

class ParameterizedHttpByQueryStringTestApi extends RegistrableMock {

  private val rootApiPath = "restmagic"

  private val helloWorldJsonByParamApi = ParameterizedHttpConfig(
    httpMethod = GET,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "helloworld" / "json",
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
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/helloworld/json?lang={{langId}}"
  )

  private val postByParamApi = ParameterizedHttpConfig(
    httpMethod = POST,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "post",
    produces = `application/json`,
    dataMode = Inline(),
    responseData = Map(
      "1" -> """{ "response": "Hello World" }""",
      "2" -> """{ "response": "Merhaba Dunya" }""",
      "3" -> """{ "response": "Salve Mondo" }""",
      "4" -> """{ "response": "Hola Mundo" }""",
      "5" -> """{ "response": "Halo Welt" }"""
    ),
    paramName = "lang",
    serveMode = ByParam(),
    formMode = ByFormData(),
    validate = true,
    displayName = "Parameterized Http Post",
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/post"
  )

  private val putByParamApi = ParameterizedHttpConfig(
    httpMethod = PUT,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "put",
    produces = `application/json`,
    dataMode = Inline(),
    responseData = Map(
      "1" -> """{ "response": "Hello World" }""",
      "2" -> """{ "response": "Merhaba Dunya" }""",
      "3" -> """{ "response": "Salve Mondo" }""",
      "4" -> """{ "response": "Hola Mundo" }""",
      "5" -> """{ "response": "Halo Welt" }"""
    ),
    paramName = "lang",
    serveMode = ByParam(),
    formMode = ByFormData(),
    validate = true,
    displayName = "Parameterized Http Put",
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/put"
  )

  private val deleteByParamApi = ParameterizedHttpConfig(
    httpMethod = DELETE,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "delete",
    produces = `application/json`,
    dataMode = Inline(),
    responseData = Map(
      "1" -> """{ "response": "Hello World" }""",
      "2" -> """{ "response": "Merhaba Dunya" }""",
      "3" -> """{ "response": "Salve Mondo" }""",
      "4" -> """{ "response": "Hola Mundo" }""",
      "5" -> """{ "response": "Halo Welt" }"""
    ),
    paramName = "lang",
    serveMode = ByParam(),
    formMode = ByFormData(),
    validate = true,
    displayName = "Parameterized Http Delete",
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/delete"
  )

  // http://localhost:8085/restmagic/examples/parameterizedhttp/error/querystring/get&errorId=1

  private val errorWithGetByQueyStringParamApi = ParameterizedHttpErrorConfig(
    httpMethod = GET,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "error" / "querystring" / "get",
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
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/error/querystring/get/{{errorId}}"
  )

  private val errorWithPostByQueyStringParamApi = ParameterizedHttpErrorConfig(
    httpMethod = POST,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "error" / "querystring" / "post",
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
    displayName = "Parameterized Http Error Post by query string",
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/error/querystring/post/{{errorId}}"
  )

  private val errorWithGetByFormDataParamApi = ParameterizedHttpErrorConfig(
    httpMethod = GET,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "error" / "formdata" / "get",
    responseData = Map(
      "1" -> ErrorCode(BadRequest, "RestMagic BadRequest error"),
      "2" -> ErrorCode(BandwidthLimitExceeded, "RestMagic BandwidthLimitExceeded error"),
      "3" -> ErrorCode(GatewayTimeout, "RestMagic GatewayTimeout error"),
      "4" -> ErrorCode(NetworkConnectTimeout, "RestMagic NetworkConnectTimeout error"),
      "5" -> ErrorCode(RequestUriTooLong, "RestMagic RequestUriTooLong error")
    ),
    paramName = "errorId",
    serveMode = ByParam(),
    formMode = ByFormData(),
    displayName = "Parameterized Http Error Get by form data",
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/error/formdata/get/{{errorId}}"
  )

  private val errorWithPostByFormDataParamApi = ParameterizedHttpErrorConfig(
    httpMethod = POST,
    apiPath = rootApiPath / "examples" / "parameterizedhttp" / "error" / "formdata" / "post",
    responseData = Map(
      "1" -> ErrorCode(BadRequest, "RestMagic BadRequest error"),
      "2" -> ErrorCode(BandwidthLimitExceeded, "RestMagic BandwidthLimitExceeded error"),
      "3" -> ErrorCode(GatewayTimeout, "RestMagic GatewayTimeout error"),
      "4" -> ErrorCode(NetworkConnectTimeout, "RestMagic NetworkConnectTimeout error"),
      "5" -> ErrorCode(RequestUriTooLong, "RestMagic RequestUriTooLong error")
    ),
    paramName = "errorId",
    serveMode = ByParam(),
    formMode = ByFormData(),
    displayName = "Parameterized Http Error Post by form data",
    displayUrl = "/" + rootApiPath + "/examples/parameterizedhttp/error/formdata/post/{{errorId}}"
  )

  def getApiConfig: List[RootApiConfig] = {
    List(
      helloWorldJsonByParamApi,
      postByParamApi,
      putByParamApi,
      deleteByParamApi,
      errorWithGetByQueyStringParamApi,
      errorWithPostByQueyStringParamApi,
      errorWithGetByFormDataParamApi,
      errorWithPostByFormDataParamApi
    )
  }

}