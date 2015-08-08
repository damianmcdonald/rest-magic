package com.github.damianmcdonald.restmagic.configurators

import java.io.FileNotFoundException

import com.github.damianmcdonald.restmagic.configurators.BinaryMode.Attachment
import org.specs2.mutable.Specification
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.PathMatcher0

class FileDownloadConfigSpec extends Specification {

  "A FileDownloadConfig" should {
    "return a valid FileDownloadConfig class instance using a minimal constructor" in {
      val fileDownloadConfig = FileDownloadConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/pdf`,
        filePath = "/sample.pdf"
      )
      fileDownloadConfig must not be null
    }
  }

  "A FileDownloadConfig" should {
    "return a valid FileDownloadConfig class instance using a full constructor" in {
      val fileDownloadConfig = FileDownloadConfig(
        httpMethod = GET,
        apiPath = "examples",
        produces = `application/pdf`,
        filePath = "/sample.pdf",
        binaryMode = Attachment(),
        displayName = "File Download Attachment Get example",
        displayUrl = "/examples/filedownload/attachment"
      )
      fileDownloadConfig must not be null
    }
  }

  "A FileDownloadConfig" should {
    "throw an assertion error" in {
      "when a  FileDownloadConfig minimal constructor contains an empty file path" in {
        def fileDownloadConfig = FileDownloadConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/pdf`,
          filePath = ""
        )
        fileDownloadConfig must throwA[AssertionError]
      }
    }
  }

  "A FileDownloadConfig" should {
    "throw an assertion error" in {
      "when a  FileDownloadConfig minimal constructor contains an invalid file path" in {
        def fileDownloadConfig = FileDownloadConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/pdf`,
          filePath = "/some/path/tofile"
        )
        fileDownloadConfig must throwA[FileNotFoundException]
      }
    }
  }

  "A FileDownloadConfig" should {
    "throw an assertion error" in {
      "when a  FileDownloadConfig full constructor contains an empty file path" in {
        def fileDownloadConfig = FileDownloadConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/pdf`,
          filePath = "",
          binaryMode = Attachment(),
          displayName = "File Download Attachment Get example",
          displayUrl = "/examples/filedownload/attachment"
        )
        fileDownloadConfig must throwA[AssertionError]
      }
    }
  }

  "A FileDownloadConfig" should {
    "throw an assertion error" in {
      "when a  FileDownloadConfig full constructor contains an invalid file path" in {
        def fileDownloadConfig = FileDownloadConfig(
          httpMethod = GET,
          apiPath = "examples",
          produces = `application/pdf`,
          filePath = "/some/path/tofile",
          binaryMode = Attachment(),
          displayName = "File Download Attachment Get example",
          displayUrl = "/examples/filedownload/attachment"
        )
        fileDownloadConfig must throwA[FileNotFoundException]
      }
    }
  }

}
