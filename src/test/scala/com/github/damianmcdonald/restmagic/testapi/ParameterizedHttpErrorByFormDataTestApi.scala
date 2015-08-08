package com.github.damianmcdonald.restmagic.testapi

import com.github.damianmcdonald.restmagic.configurators.FormMode.{ FormModeType, ByFormData, ByQueryString }
import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.configurators.DataMode._
import com.github.damianmcdonald.restmagic.configurators.ServeMode._
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.HttpMethod
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.Directives._
import spray.http.StatusCodes._
import spray.routing._

class ParameterizedHttpErrorByFormDataTestApi extends RegistrableMock {

  private val rootApiPath = "restmagic"

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
      errorWithGetByQueyStringParamApi,
      errorWithPostByQueyStringParamApi,
      errorWithGetByFormDataParamApi,
      errorWithPostByFormDataParamApi
    )
  }

}