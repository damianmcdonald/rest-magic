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

package com.github.damianmcdonald.restmagic

import com.github.damianmcdonald.restmagic.configurators.DataMode._
import com.github.damianmcdonald.restmagic.configurators.FormMode.ByQueryString
import com.github.damianmcdonald.restmagic.configurators.ServeMode._
import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.configurators.utils.ConfiguratorUtils
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.Directives._

/**
 * To get started creating mocked web services,
 * you need to create a class that mixes in the traits:
 *
 * 1) com.github.damianmcdonald.restmagic.system.RegistrableMock
 * 2) com.github.damianmcdonald.restmagic.configurators.utils.ConfiguratorUtils
 *
 * rest-magic will search the package com.github.damianmcdonald.restmagic
 * for any classes mixing in the RegistrableMock trait and will create web services
 * for the definitions contained within those classes.
 *
 * Optionally, if you have defined an alternate package via the mockapi.package
 * property of /src/main/resouces/application.conf, then that package will also
 * be searched for any classes mixing in the RegistrableMock trait and will create
 * web services for the definitions contained within those classes.
 *
 * All the web services defined in this class will appear in the rest-magic
 * api registry that can be accessed at:
 *
 * protocol://hostname:port/restmagic/registry/registry.html
 */
class MyMockApi extends RegistrableMock with ConfiguratorUtils {

  /**
   * Creates a simple rest api service that:
   *
   * GET http verb on endpoint; protocol://hostname:port/restmagic/welcome
   * Responds with the MIME type; text/html and with a value of <h1>Hello World</h1>
   */
  private val simpleRestApi = SimpleRestConfig(
    httpMethod = GET,
    apiPath = "restmagic" / "welcome",
    produces = `text/html`,
    dataMode = Inline(),
    responseData = """<h1>Hello World</h1>""",
    validate = true,
    displayName = "Simple Rest Api",
    displayUrl = "/restmagic/welcome"
  )

  /**
   * Creates a parameterized rest api service that:
   *
   * GET http verb on endpoint; protocol://hostname:port/restmagic/welcome/{{parameter}}
   * Responds with the MIME type; application/json and with the value that corresponds to parameter == responseData.key
   *
   * A concrete example of using this service is:
   *
   * GET request to http://localhost:8085/restmagic/welcome/spanish
   * Responds with application/json value { "response": "Hola Mundo" }
   */
  private val parameterizedRestApi = ParameterizedRestConfig(
    httpMethod = GET,
    apiPath = "restmagic" / "welcome" / MATCH_PARAM,
    produces = `application/json`,
    dataMode = Inline(),
    responseData = Map(
      "english" -> """{ "response": "Hello World" }""",
      "turkish" -> """{ "response": "Merhaba Dunya" }""",
      "italian" -> """{ "response": "Salve Mondo" }""",
      "spanish" -> """{ "response": "Hola Mundo" }""",
      "german" -> """{ "response": "Halo Welt" }"""
    ),
    serveMode = ByParam(),
    validate = true,
    displayName = "Parameterized Api with language parameter",
    displayUrl = "/restmagic/welcome/{{parameter}}"
  )

  /**
   * Creates a parameterized http by query string api service that:
   *
   * GET http verb on endpoint; protocol://hostname:port/restmagic/character?characterId={{characterId}}
   * Responds with the MIME type; application/json and with the value that corresponds to characterId == responseData.key
   *
   * A concrete example of using this service is:
   *
   * GET request to http://localhost:8085/restmagic/character?characterId=3
   * Responds with application/json value { "character": "Yoda" }
   */
  private val parameterizedHttpByQueryStringApi = ParameterizedHttpConfig(
    httpMethod = GET,
    apiPath = "restmagic" / "character",
    produces = `application/json`,
    dataMode = Inline(),
    responseData = Map(
      "1" -> """{ "character": "Luke Skywalker" }""",
      "2" -> """{ "character": "Darth Vader" }""",
      "3" -> """{ "character": "Yoda" }""",
      "4" -> """{ "character": "Han Solo" }""",
      "5" -> """{ "character": "Chewbacca" }"""
    ),
    paramName = "characterId",
    serveMode = ByParam(),
    formMode = ByQueryString(),
    validate = true,
    displayName = "Parameterized Http with characterId parameter",
    displayUrl = "/restmagic/character?characterId={{characterId}}"
  )

  /**
   * The RegistrableMock trait requires the implementation of getApiConfig.
   * This method returns a List containing all of the api configurations
   * to be transformed into web services.
   */
  override def getApiConfig = {
    List(
      simpleRestApi,
      parameterizedRestApi,
      parameterizedHttpByQueryStringApi
    )
  }

  /**
   * Next steps
   *
   * 1) Read the project documentation; //TODO INSERT DOCUMENTATION LINK
   *
   * 2) View the test cases in /src/test/scala/com/github/damianmcdonald/restmagic/testapi.
   * 	These contain examples for creating each of the various config types available.
   *
   * 3) Take a look at the action-figure-magic project. This project is designed to demonstrate the use
   * 	of rest-magic through the creation of a fictitious website;
   *
   *   	https://github.com/damianmcdonald/action-figure-magic
   */
}
