package com.github.damianmcdonald.restmagic.services

import java.io._

import akka.actor.ActorSystem
import akka.event.slf4j.SLF4JLogging
import com.github.damianmcdonald.restmagic.configurators.{ FileUploadErrorConfig, FileUploadConfig }
import com.github.damianmcdonald.restmagic.system.Configuration
import spray.http.StatusCodes
import spray.routing._

import scala.util.Random

class FileUploadErrorService(cfg: FileUploadErrorConfig)(implicit system: ActorSystem) extends Directives with RootMockService with SLF4JLogging {

  lazy val route =
    path(cfg.apiPath) {
      post {
        formFields(cfg.fileParamName.as[Array[Byte]]) { (file) =>
          detach() {
            complete {
              (cfg.errorCode, cfg.errorMessage)
            }
          }
        }
      }
    }

}
