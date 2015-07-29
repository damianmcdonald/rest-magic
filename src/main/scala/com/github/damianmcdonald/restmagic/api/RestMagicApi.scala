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

package com.github.damianmcdonald.restmagic.api

import akka.actor.{ ActorSystem, Props }
import com.github.damianmcdonald.restmagic.RestMagicSetup
import com.github.damianmcdonald.restmagic.configurators.FormMode.{ ByFormData, ByQueryString }
import com.github.damianmcdonald.restmagic.configurators.{ RootApiConfig, SimpleRestConfig }
import com.github.damianmcdonald.restmagic.services._
import spray.http.StatusCodes
import spray.routing.{ Directives, _ }
import java.lang.reflect.Method
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import com.github.damianmcdonald.restmagic.configurators.SimpleRestErrorConfig
import com.github.damianmcdonald.restmagic.configurators.ParameterizedRestConfig
import com.github.damianmcdonald.restmagic.configurators.ParameterizedRestErrorConfig
import com.github.damianmcdonald.restmagic.configurators.ParameterizedHttpConfig

trait AbstractSystem {
  implicit def system: ActorSystem
}

trait RestMagicApi extends RouteConcatenation with StaticRoute with AbstractSystem {

  private def getRegistrableMocks: List[RootApiConfig] = {
    import org.reflections.Reflections
    import scala.collection.JavaConversions._
    val reflections = new Reflections("com.github.damianmcdonald.restmagic")
    val subclasses = reflections.getSubTypesOf(classOf[RegistrableMock])
    val registrables: List[RegistrableMock] = subclasses.toList.map { x => x.getDeclaredConstructors()(0).newInstance().asInstanceOf[RegistrableMock] }
    registrables.map(e => e.getApiConfig).flatten
  }

  private def configToService(xs: List[RootApiConfig]): List[RootMockService] = {
    xs map (e => {
      e match {
        case cfg: SimpleRestConfig => {
          println("INFO >>> " + cfg.registeredApi.getOrElse("No registration details available for this SimpleRest Api"))
          new SimpleRestService(cfg)
        }
        case cfg: SimpleRestErrorConfig => {
          println("INFO >>> " + cfg.registeredApi.getOrElse("No registration details available for this SimpleRestError Api"))
          new SimpleRestErrorService(cfg)
        }
        case cfg: ParameterizedRestConfig => {
          println("INFO >>> " + cfg.registeredApi.getOrElse("No registration details available for this ParameterizedRest Api"))
          new ParameterizedRestService(cfg)
        }
        case cfg: ParameterizedRestErrorConfig => {
          println("INFO >>> " + cfg.registeredApi.getOrElse("No registration details available for this ParameterizedRestError Api"))
          new ParameterizedRestErrorService(cfg)
        }
        case cfg: ParameterizedHttpConfig => {
          println("INFO >>> " + cfg.registeredApi.getOrElse("No registration details available for this ParameterizedHttp Api"))
          cfg.formMode match {
            case ByQueryString() => new ParameterizedHttpByQueryStringService(cfg)
            case ByFormData() => new ParameterizedHttpByFormDataService(cfg)
          }

        }
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

    val xs = configToService(getRegistrableMocks)
    concatRoutes(xs) ~ staticRoute
  }
}

trait StaticRoute extends Directives {
  this: AbstractSystem =>

  lazy val staticRoute =
    pathPrefix("images") {
      getFromResourceDirectory("web/assets/images/")
    } ~
      pathEndOrSingleSlash {
        getFromResource("web/index.html")
      } ~ complete(StatusCodes.NotFound)
}