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

package com.github.damianmcdonald.restmagic.system

import java.io.File

import akka.actor.ActorSystem
import akka.io.IO
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import spray.can.Http
import spray.routing.Directives

object RestMagicSystem extends App with RestMagicApi {
  implicit lazy val system = ActorSystem("restmagic-system")
  sys.addShutdownHook({ system.shutdown })
  IO(Http) ! Http.Bind(rootService, Configuration.host, Configuration.port)
}

object Configuration extends Directives {
  import com.typesafe.config.ConfigFactory
  import org.apache.commons.lang3.SystemUtils

  // load configuration settings from application.conf
  // in the default location
  private val config = ConfigFactory.load
  config.checkValid(ConfigFactory.defaultReference)

  lazy val host = config.getString("restmagic.host")

  lazy val port = config.getString("restmagic.port").toInt

  lazy val staticPathName = config.getString("restmagic.static.path.name")

  lazy val staticIndex = config.getString("restmagic.static.index")

  lazy val stubsDir = {
    if (config.getString("restmagic.stubs.root").isEmpty) {
      ""
    } else {
      val file = new File(config.getString("restmagic.stubs.root"))
      if (file.exists()) file.getAbsolutePath else ""
    }
  }

  lazy val uploadsDir = {
    if (config.getString("restmagic.upload.root").isEmpty) {
      ""
    } else {
      val file = new File(config.getString("restmagic.downloads.root"))
      if (file.exists()) file.getAbsolutePath else ""
    }
  }

  lazy val downloadsDir = {
    if (config.getString("restmagic.downloads.root").isEmpty) {
      ""
    } else {
      val file = new File(config.getString("restmagic.downloads.root"))
      if (file.exists()) file.getAbsolutePath else ""
    }
  }

}
