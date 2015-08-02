package com.github.damianmcdonald.restmagic.testapi

import com.github.damianmcdonald.restmagic.configurators.BinaryMode._
import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import com.sun.xml.internal.ws.api.message.AttachmentEx
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.Directives._

class FileDownloadTestApi extends RegistrableMock {

  private val rootApiPath = "restmagic"

  private val getFileDownloadAttachmentExampleApi = FileDownloadConfig(
    httpMethod = GET,
    apiPath = rootApiPath / "examples" / "filedownload" / "attachment",
    produces = `application/pdf`,
    filePath = "/sample.pdf",
    binaryMode = Attachment(),
    displayName = "File Download Attachment Get example",
    displayUrl = "/" + rootApiPath + "/examples/filedownload/attachment"
  )

  private val getFileDownloadInlineExampleApi = FileDownloadConfig(
    httpMethod = GET,
    apiPath = rootApiPath / "examples" / "filedownload" / "inline",
    produces = `application/pdf`,
    filePath = "/sample.pdf",
    binaryMode = Inline(),
    displayName = "File Download Inline Get example",
    displayUrl = "/" + rootApiPath + "/examples/filedownload/inline"
  )

  def getApiConfig: List[RootApiConfig] = {
    List(
      getFileDownloadAttachmentExampleApi,
      getFileDownloadInlineExampleApi
    )
  }

}