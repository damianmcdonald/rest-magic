package com.github.damianmcdonald.restmagic.testapi

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

class FileUploadTestApi extends RegistrableMock {

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

  def getApiConfig: List[RootApiConfig] = {
    List(
      postFileUploadExampleApi
    )
  }

}