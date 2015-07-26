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

package com.github.damianmcdonald.api

import akka.actor.{ ActorSystem, Props }
import com.github.damianmcdonald.RestMagicSetup
import com.github.damianmcdonald.configurators.{ RootApiConfig, SimpleRestConfig }
import com.github.damianmcdonald.services.{ RootMockService, SimpleRestService }
import spray.http.StatusCodes
import spray.routing.{ Directives, _ }

trait AbstractSystem {
  implicit def system: ActorSystem
}

trait RestMagicApi extends RouteConcatenation with StaticRoute with AbstractSystem {

  def configToService(xs: List[RootApiConfig]): List[RootMockService] = {
    xs map (e => {
      e match {
        case cfg: SimpleRestConfig => new SimpleRestService(cfg)
        case _ => throw new MatchError("Unable to find match for service!!!!!")
      }
    })
  }

  val rootService = system.actorOf(Props(new RootService[BasicRouteActor](routes)), "routes")
  lazy val routes = {
    def concatRoutes(xs: List[RootMockService]) = {
      def inner(xss: List[RootMockService], accum: Route): Route = {
        xss match {
          case Nil => accum
          case head :: tail => inner(tail, accum ~ head.route)
        }
      }
      val startElm = {
        xs.headOption match {
          case Some(e) => e
          case None => throw new IllegalArgumentException
        }
      }
      inner(xs.drop(0), startElm.route)
    }

    val xs = configToService(RestMagicSetup.getApiConfig)
    concatRoutes(xs)
  }
}

trait StaticRoute extends Directives {
  this: AbstractSystem =>

  lazy val staticRoute =
    pathPrefix("scripts") {
      getFromResourceDirectory("web/scripts/")
    } ~
      pathPrefix("css") {
        getFromResourceDirectory("web/css/")
      } ~
      pathPrefix("images") {
        getFromResourceDirectory("web/images/")
      } ~
      pathPrefix("fonts") {
        getFromResourceDirectory("web/fonts/")
      } ~
      pathEndOrSingleSlash {
        getFromResource("web/index.html")
      } ~ complete(StatusCodes.NotFound)
}
