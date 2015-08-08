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
import spray.http.MediaTypes._
import spray.routing.Directives

class ApiRegistryService(apis: Map[String, List[RegisteredApi]])(implicit system: ActorSystem) extends Directives with RootMockService with SLF4JLogging {

  val image = """<img src="data:image/png;base64,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"/>"""

  def getResponseDataTemplate(displayName: String, responseData: Map[String, String]): String = {

    def processResponseData(param: String = "None", value: String): String = {
      s"""
         |      <div class="container-fluid">
         |        <p class="lead">Response to param: $param</p>
         |        <pre class="bg-success" style="overflow: scroll-y;">
         |$value
         |      </pre>
         |      </div>
       """.stripMargin
    }

    def generateTemplate(responseTemplate: String): String = {
      s"""
         |<!DOCTYPE html>
         |<html lang="en">
         |  <head>
         |    <meta charset="utf-8">
         |    <meta http-equiv="X-UA-Compatible" content="IE=edge">
         |    <meta name="viewport" content="width=device-width, initial-scale=1">
         |    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
         |    <title>$displayName Response Data</title>
         |
         |    <!-- Bootstrap -->
         |    <!-- Latest compiled and minified CSS -->
         |    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
         |
         |    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
         |    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
         |    <!--[if lt IE 9]>
         |      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
         |      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
         |      <![endif]-->
         |  </head>
         |    <body>
         |
         |      <div class="jumbotron">
         |        <div class="container">
         |          <h1>$image RestMagic <small>$displayName Response Data</small></h1>
         |        </div>
         |      </div>
         |
         |      <div class="container-fluid">
         |        <h2>$displayName -- Response Data</h2>
         |      </div>
         |
         |      <hr>
         |
         |      $responseTemplate
         |
         |      </div>
         |
         |  </body>
         |
         |</html>
         |
         |
         |
       """.stripMargin
    }

    generateTemplate(responseData.map({ case (k, v) => processResponseData(k, v) }).mkString("<hr>"))

  }

  lazy val route =
    path("restmagic" / "api" / "registry") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            val apiContent = apis.map({ case (k, v) => """{ "directive":"""" + k.toString + """",""" + """"registeredApis": [ """ + v.map(api => api.toJson).mkString(",") + """] }""" }).mkString(",")
            if (apis.isEmpty) """{ "status": "No registered API's" }"""
            else {
              import net.liftweb.json._
              implicit val formats = net.liftweb.json.DefaultFormats
              val json = s"""{"apis": [ $apiContent ] }"""
              pretty(render(parse(json)))
            }
          }
        }
      }
    } ~
      path("restmagic" / "api" / "registry" / "responsedata" / """\w+""".r) { param =>
        get {
          respondWithMediaType(`text/html`) {
            complete {
              val apiList = apis.map({ case (k, v) => v }).flatten.filter(_.id.equals(param))
              val api: RegisteredApi = apiList.headOption.getOrElse(throw new RuntimeException("Unable to find matching registry entry for: " + param))
              getResponseDataTemplate(api.displayName, api.responseData)
            }
          }
        }
      }

}
