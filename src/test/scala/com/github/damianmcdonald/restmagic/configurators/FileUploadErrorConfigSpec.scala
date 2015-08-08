package com.github.damianmcdonald.restmagic.configurators

import com.github.damianmcdonald.restmagic.configurators.DataMode.Inline
import org.specs2.mutable.Specification
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.http.StatusCodes

class FileUploadErrorConfigSpec extends Specification {

  "A FileUploadErrorConfig" should {
    "return a valid FileUploadErrorConfig class instance using a minimal constructor" in {
      val fileUploadErrorConfig = FileUploadErrorConfig(
        httpMethod = POST,
        apiPath = "examples",
        errorCode = StatusCodes.InternalServerError,
        errorMessage = "File upload has failed!",
        fileParamName = "myfile"
      )
      fileUploadErrorConfig must not be null
    }
  }

  "A FileUploadErrorConfig" should {
    "return a valid FileUploadErrorConfig class instance using a full constructor" in {
      val fileUploadErrorConfig = FileUploadErrorConfig(
        httpMethod = POST,
        apiPath = "examples",
        errorCode = StatusCodes.InternalServerError,
        errorMessage = "File upload has failed!",
        fileParamName = "myfile",
        displayName = "File Upload Error Post example",
        displayUrl = "/examples/fileupload/error/post"
      )
      fileUploadErrorConfig must not be null
    }
  }

  "A FileUploadErrorConfig" should {
    "throw an assertion error" in {
      "when a  FileUploadErrorConfig minimal constructor contains empty fileParamName" in {
        def fileUploadErrorConfig = FileUploadErrorConfig(
          httpMethod = POST,
          apiPath = "examples",
          errorCode = StatusCodes.InternalServerError,
          errorMessage = "File upload has failed!",
          fileParamName = ""
        )
        fileUploadErrorConfig must throwA[AssertionError]
      }
    }
  }

  "A FileUploadErrorConfig" should {
    "throw an assertion error" in {
      "when a  FileUploadErrorConfig full constructor contains empty fileParamName" in {
        def fileUploadErrorConfig = FileUploadErrorConfig(
          httpMethod = POST,
          apiPath = "examples",
          errorCode = StatusCodes.InternalServerError,
          errorMessage = "File upload has failed!",
          fileParamName = "",
          displayName = "File Upload Error Post example",
          displayUrl = "/examples/fileupload/error/post"
        )
        fileUploadErrorConfig must throwA[AssertionError]
      }
    }
  }

}
