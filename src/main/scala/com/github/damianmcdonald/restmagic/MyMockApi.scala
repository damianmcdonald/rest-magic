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
import com.github.damianmcdonald.restmagic.configurators._
import com.github.damianmcdonald.restmagic.system.RegistrableMock
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.routing.Directives._

class MyMockApi extends RegistrableMock {

  private val welcomeJsonApi = SimpleRestConfig(
    httpMethod = GET,
    apiPath = "restmagic" / "welcome",
    produces = `text/html`,
    dataMode = Inline(),
    responseData = """<h1>Hello World</h1>""",
    validate = true,
    displayName = "Welcome Api",
    displayUrl = "/restmagic/welcome"
  )

  private val helloWorldJsonApi = SimpleRestConfig(
    httpMethod = GET,
    apiPath = "restmagic" / "hello",
    produces = `application/json`,
    dataMode = Inline(),
    responseData = """{ "response": "Merhaba Dunya" }""",
    validate = true,
    displayName = "Hello World Api",
    displayUrl = "/restmagic/hello"
  )

  override def getApiConfig = {
    List(
      welcomeJsonApi,
      helloWorldJsonApi
    )
  }
}
