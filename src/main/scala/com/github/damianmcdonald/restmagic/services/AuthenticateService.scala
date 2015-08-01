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
import spray.http.StatusCodes
import spray.routing.Directives
import spray.routing.authentication.{ BasicAuth, UserPass }
import spray.routing.directives.AuthMagnet

import scala.concurrent.{ ExecutionContext, Future }

class UserDetails(val userName: String, val password: String, val isAuthenticated: Boolean)

class AuthenticateService(implicit system: ActorSystem) extends Directives with RootMockService with SLF4JLogging {

  implicit val ec: ExecutionContext = system.dispatcher

  def isCredentialValid(userPass: Option[UserPass]): Boolean = {
    val credentials: Map[String, String] = Map(
      "luke" -> "12345",
      "han" -> "12345",
      "vader" -> "12345",
      "leia" -> "12345",
      "chewy" -> "12345"
    )

    userPass match {
      case Some(UserPass(user, pass)) => {
        credentials.get(user) match {
          case Some(v) if (v.equals(pass)) => true
          case Some(v) if (!v.equals(pass)) => false
          case None => false
        }
      }
      case _ => false
    }
  }

  def isAuthorizedForSecuredPage(userName: String): Boolean = {
    userName == "luke"
  }

  def basicUserAuthenticator(implicit ec: ExecutionContext): AuthMagnet[UserDetails] = {
    def validateUser(userPass: Option[UserPass]): Option[UserDetails] = {
      if (isCredentialValid(userPass)) {
        userPass match {
          case Some(UserPass(user, pass)) => Some(new UserDetails(user, pass, true))
          case None => Some(new UserDetails("", "", false))
        }
      } else Some(new UserDetails("", "", false))
    }

    def authenticator(userPass: Option[UserPass]): Future[Option[UserDetails]] = Future {
      validateUser(userPass)
    }

    BasicAuth(authenticator _, realm = "RestMagic Secure API")
  }

  def myUserPassAuthenticator(userPass: Option[UserPass]): Future[Option[UserDetails]] =
    Future {
      if (isCredentialValid(userPass)) {
        userPass match {
          case Some(UserPass(user, pass)) => Some(new UserDetails(user, pass, true))
          case None => Some(new UserDetails("", "", false))
        }
      } else Some(new UserDetails("", "", false))
    }

  lazy val route =
    pathPrefix("authenticate") {
      authenticate(basicUserAuthenticator) { userDetails: UserDetails =>
        path("secured") {
          // authorize(isAuthorizedForSecuredPage(userDetails.userName)) {
          complete {
            if (isAuthorizedForSecuredPage(userDetails.userName)) "Authorized successfully" else (StatusCodes.Forbidden, "Authorization failure!")
          }
        } ~
          path("unsecured") {
            complete {
              if (userDetails.isAuthenticated) "Authenticated successfully" else (StatusCodes.Unauthorized, "Authentication failure!")
            }
          }
      }
    }

}
