package com.github.damianmcdonald.restmagic.configurators

import spray.routing.{ Directive0, PathMatcher0 }
import spray.http.StatusCode
import com.github.damianmcdonald.restmagic.configurators.utils.ConfiguratorUtils
import spray.http.HttpMethod

object SimpleRestErrorConfig extends ConfiguratorUtils {

  def apply(
    httpMethod: HttpMethod,
    apiPath: PathMatcher0,
    errorCode: StatusCode,
    errorMessage: String
  ): SimpleRestErrorConfig = {
    val directive = httpMethodToDirective(httpMethod)
    new SimpleRestErrorConfig(directive, apiPath, errorCode, errorMessage)
  }

  def apply(
    httpMethod: HttpMethod,
    apiPath: PathMatcher0,
    errorCode: StatusCode,
    errorMessage: String,
    displayName: String,
    displayUrl: String
  ): SimpleRestErrorConfig = {
    val directive = httpMethodToDirective(httpMethod)
    val registeredApi = {
      Option(
        new RegisteredApi(
          displayName,
          displayUrl,
          httpMethod.toString,
          "Http errors",
          dataModeTypeToString(DataMode.Inline()),
          serveModeTypeToString(ServeMode.Singular()),
          Map(errorCode.toString -> errorMessage),
          API_TYPE_SIMPLE_REST_ERROR
        )
      )
    }
    new SimpleRestErrorConfig(directive, apiPath, errorCode, errorMessage, registeredApi)
  }

}

case class SimpleRestErrorConfig(
  httpMethod: Directive0,
  apiPath: PathMatcher0,
  errorCode: StatusCode,
  errorMessage: String,
  registeredApi: Option[RegisteredApi] = None
) extends RootApiConfig