package com.github.damianmcdonald.restmagic.services

import java.io._

import akka.actor.ActorSystem
import akka.event.slf4j.SLF4JLogging
import com.github.damianmcdonald.restmagic.configurators.BinaryMode.{ Inline, Attachment, BinaryModeType }
import com.github.damianmcdonald.restmagic.configurators.{ FileDownloadConfig, FileUploadConfig }
import com.github.damianmcdonald.restmagic.system.Configuration
import spray.http.HttpHeaders.{ `Content-Disposition`, `Content-Type` }
import spray.http._
import spray.routing._
import org.apache.commons.io.FileUtils

import scala.util.Random

class FileDownloadService(cfg: FileDownloadConfig)(implicit system: ActorSystem) extends Directives with RootMockService with SLF4JLogging {

  lazy val route =
    path(cfg.apiPath) {
      cfg.httpMethod {
        respondWithHeaders(getBinaryHeaders(cfg.binaryMode, cfg.produces, cfg.filePath.getName)) {
          complete {
            val bytes: Array[Byte] = FileUtils.readFileToByteArray(cfg.filePath);
            HttpResponse(entity = HttpEntity(cfg.produces, HttpData(bytes)))
          }
        }
      }
    }

  def getBinaryHeaders(binaryMode: BinaryModeType, produces: MediaType, fileName: String): List[HttpHeader] = {
    val header1 = `Content-Type`(cfg.produces)
    val header2 = {
      val binary = {
        binaryMode match {
          case Inline() => "inline"
          case Attachment() => "attachment"
        }
      }
      `Content-Disposition`(binary, Map("filename" -> fileName))
    }
    List(header1, header2)
  }

}
