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

package com.github.damianmcdonald

import com.github.damianmcdonald.configurators.DataMode._
import com.github.damianmcdonald.configurators.ServeMode._
import com.github.damianmcdonald.configurators._
import spray.http.MediaTypes._
import spray.routing.Directives._

object RestMagicSetup {

  // API Declaratons

  val helloWorldResponse = {
    """
      |<html>
      |<head>
      |</head>
      |<body>
      |<h1>Hello World</h1>
      |</body>
      |</html>
    """.stripMargin
  }

  val helloWorldHtmlApi = SimpleRestConfig(
    get,
    "path" / "path2" / "html",
    `text/html`,
    Inline(),
    Map("1" -> helloWorldResponse),
    Singluar(),
    true
  )

  val helloWorldJsonApi = SimpleRestConfig(
    get,
    "path" / "path2" / "json",
    `application/json`,
    Inline(),
    Map("1" -> """{ "response": "Hello World" } """),
    Singluar(),
    true
  )

  val helloWorldXmlApi = SimpleRestConfig(
    get,
    "path" / "path2" / "xml",
    `text/html`,
    Inline(),
    Map("1" -> """<root><response>Hello World</response></root>"""),
    Singluar(),
    true
  )

  val helloWorldFileApi = SimpleRestConfig(
    get,
    "hello" / "world" / "file",
    `application/json`,
    FileStub(),
    Map("1" -> "/example_json.json"),
    Singluar(),
    true
  )

  def getApiConfig: List[RootApiConfig] = {
    List(
      helloWorldHtmlApi,
      helloWorldJsonApi,
      helloWorldXmlApi,
      helloWorldFileApi
    )
  }

}
