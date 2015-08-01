package com.github.damianmcdonald.restmagic.configurators

import spray.routing.{ Directive0, PathMatcher0 }
import spray.http.StatusCode
import com.github.damianmcdonald.restmagic.configurators.utils.ConfiguratorUtils
import spray.http.HttpMethod

object FileUploadErrorConfig extends ConfiguratorUtils {

  def apply(
    httpMethod: HttpMethod,
    apiPath: PathMatcher0,
    errorCode: StatusCode,
    errorMessage: String,
    fileParamName: String
  ): FileUploadErrorConfig = {
    val directive = httpMethodToDirective(httpMethod)
    new FileUploadErrorConfig(directive, apiPath, errorCode, errorMessage, fileParamName)
  }

  def apply(
    httpMethod: HttpMethod,
    apiPath: PathMatcher0,
    errorCode: StatusCode,
    errorMessage: String,
    fileParamName: String,
    displayName: String,
    displayUrl: String
  ): FileUploadErrorConfig = {
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
          API_TYPE_FILE_UPLOAD_ERROR
        )
      )
    }
    new FileUploadErrorConfig(directive, apiPath, errorCode, errorMessage, fileParamName, registeredApi)
  }

}

case class FileUploadErrorConfig(
  httpMethod: Directive0,
  apiPath: PathMatcher0,
  errorCode: StatusCode,
  errorMessage: String,
  fileParamName: String,
  registeredApi: Option[RegisteredApi] = None
) extends RootApiConfig