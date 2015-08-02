/*
 * Copyright 2015 Damian McDonald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.damianmcdonald.restmagic.services

import akka.actor.ActorSystem
import akka.event.slf4j.SLF4JLogging
import com.github.damianmcdonald.restmagic.configurators.{ RegisteredApi, SimpleRestConfig }
import spray.http.MediaTypes.`application/json`
import spray.routing.Directives

class ApiDirectoryService(apis: List[RegisteredApi])(implicit system: ActorSystem) extends Directives with RootMockService with SLF4JLogging {

  lazy val route =
    path("restmagic" / "api" / "directory") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            val start = """{"apis": ["""
            val content = apis.map { e => e.toJson } mkString ","
            val end = """] }"""
            if (apis.isEmpty) """{ "status": "No registered API's" }"""
            else
              s"""{"apis": [
                 |$content
                 | ]
                 |}""".stripMargin
          }
        }
      }
    }

}
