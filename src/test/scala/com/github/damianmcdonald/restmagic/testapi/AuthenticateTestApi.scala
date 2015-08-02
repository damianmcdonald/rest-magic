package com.github.damianmcdonald.restmagic.testapi

import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.Directives._

class AuthenticateTestApi extends RegistrableMock {

  private val rootApiPath = "restmagic"

  private val jsonAuthenticateResponse = """{ "status": "authenticated" }"""
  private val jsonAuthorizeResponse = """{ "status": "authorized" }"""

  private val postAuthenticateExampleApi = AuthenticateConfig(
    httpMethod = POST,
    securePathPrefix = rootApiPath / "examples" / "logon",
    authenticatePath = "authenticate",
    authorizePath = "authorize",
    credentials = Map("luke" -> "12345", "han" -> "qwerty", "chewy" -> "zxcvb", "yoda" -> "654321"),
    authorizedUsers = List("luke", "yoda"),
    produces = `application/json`,
    authenticateResponseData = jsonAuthenticateResponse,
    authorizeResponseData = jsonAuthorizeResponse,
    displayName = "Authenticate Post example",
    displayUrl = "/" + rootApiPath + "/examples/logon"
  )

  def getApiConfig: List[RootApiConfig] = {
    List(
      postAuthenticateExampleApi
    )
  }

}