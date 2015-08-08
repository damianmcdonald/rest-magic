package com.github.damianmcdonald.restmagic.testapi

import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.configurators.DataMode._
import com.github.damianmcdonald.restmagic.configurators.ServeMode._
import com.github.damianmcdonald.restmagic.configurators.SimpleRestConfig
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.MediaTypes._
import spray.routing.Directives._
import spray.http.StatusCodes._
import spray.http.HttpMethods._

class SimpleRestErrorTestApi extends RegistrableMock {

  private val rootApiPath = "restmagic"

  private val simpleRestInternalError = SimpleRestErrorConfig(
    httpMethod = GET,
    apiPath = rootApiPath / "examples" / "simplerest" / "error" / "500",
    errorCode = InternalServerError,
    errorMessage = "Example of 500 Internal Server Error from RestMagic",
    displayName = "Simple Rest Error example",
    displayUrl = "/" + rootApiPath + "/examples/simplerest/error/500"
  )

  def getApiConfig: List[RootApiConfig] = {
    List(
      simpleRestInternalError
    )
  }

}