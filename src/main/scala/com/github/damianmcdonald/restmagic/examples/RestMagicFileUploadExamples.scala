package com.github.damianmcdonald.restmagic.examples

import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.configurators.DataMode._
import com.github.damianmcdonald.restmagic.configurators.ServeMode._
import com.github.damianmcdonald.restmagic.configurators.SimpleRestConfig
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.MediaTypes._
import spray.http.StatusCodes
import spray.routing.Directives._
import spray.http.StatusCodes._
import spray.http.HttpMethods._

class RestMagicFileUploadExamples extends RegistrableMock {

  private val rootApiPath = "restmagic"

  private val postFileUploadExampleApi = FileUploadConfig(
    httpMethod = POST,
    apiPath = rootApiPath / "examples" / "fileupload" / "post",
    produces = `application/json`,
    dataMode = Inline(),
    responseData = """{ "status": "success" }""",
    fileParamName = "myfile",
    validate = true,
    displayName = "File Upload Post example",
    displayUrl = "/" + rootApiPath + "/examples/fileupload/post"
  )

  private val postFileUploadErrorExampleApi = FileUploadErrorConfig(
    httpMethod = POST,
    apiPath = rootApiPath / "examples" / "fileupload" / "error" / "post",
    errorCode = StatusCodes.InternalServerError,
    errorMessage = "File upload has failed!",
    fileParamName = "myfile",
    displayName = "File Upload Error Post example",
    displayUrl = "/" + rootApiPath + "/examples/fileupload/error/post"
  )

  def getApiConfig: List[RootApiConfig] = {
    List(
      postFileUploadExampleApi,
      postFileUploadErrorExampleApi
    )
  }

}