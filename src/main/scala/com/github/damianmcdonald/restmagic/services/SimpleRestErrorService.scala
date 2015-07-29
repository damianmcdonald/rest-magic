package com.github.damianmcdonald.restmagic.services

import akka.actor.ActorSystem
import com.github.damianmcdonald.restmagic.configurators.SimpleRestErrorConfig
import akka.event.slf4j.SLF4JLogging
import spray.routing.Directives

class SimpleRestErrorService(cfg: SimpleRestErrorConfig)(implicit system: ActorSystem) extends Directives with RootMockService with SLF4JLogging {

  lazy val route =
    cfg.httpMethod {
      path(cfg.apiPath) {
        complete(cfg.errorCode, cfg.errorMessage)
      }
    }

}