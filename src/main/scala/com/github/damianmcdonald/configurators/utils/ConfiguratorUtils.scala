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

package com.github.damianmcdonald.configurators.utils

import com.github.damianmcdonald.configurators.DataMode.{ DataModeType, FileStub, Inline }
import com.github.damianmcdonald.system.Configuration
import spray.http.MediaType
import spray.http.MediaTypes._

trait ConfiguratorUtils {

  val ERROR_MULTI_MODE = "Singular serve mode can only be used when the responseData Map contains a single entry. Please use Random or ByParam serve modes"
  val ERROR_EMPTY_MAP = "The responseData map can not be empty. You must provided at least one response entry"
  val ERROR_SINGULAR_MODE = "The selected serve mode can only be used when the responseData Map contains more than one entry. Please add additional responses to the Map or use Singular serve mode"

  private def fileToString(fileName: String): String = {
    if (Configuration.stubsDir.isEmpty) {
      val is = this.getClass().getResourceAsStream("/stubs" + fileName)
      scala.io.Source.fromInputStream(is).mkString
    } else {
      scala.io.Source.fromFile(Configuration.stubsDir + fileName).mkString
    }
  }

  protected def validateAndLoadResponses(dataMode: DataModeType, produces: MediaType, xs: Map[String, String]): Map[String, String] = {
    xs map {
      case (key, value) => {
        val data = dataMode match {
          case FileStub() => fileToString(value)
          case Inline() => value
          case _ => throw new MatchError("Unable to find a valid match for DataModeType")
        }
        produces match {
          case `application/json` => {
            import net.liftweb.json._
            implicit val formats = net.liftweb.json.DefaultFormats
            parse(data) // if the json is not valid we will get an exception here!
          }
          case `text/xml` => {
            scala.xml.XML.loadString(data) // if the xml is not valid we will get an exception here!
          }
          case `text/html` => {
            scala.xml.XML.loadString(data) // if the html is not valid we will get an exception here!
          }
          case _ => println("INFO >>> Noo validator available for MediaType: " + produces)
        }
        (key -> data)
      }
    }
  }

  protected def loadResponses(dataMode: DataModeType, xs: Map[String, String]): Map[String, String] = {
    xs map {
      case (key, value) => {
        val data = dataMode match {
          case FileStub() => fileToString(value)
          case Inline() => value
          case _ => throw new MatchError("Unable to find a valid match for DataModeType")
        }
        (key -> data)
      }
    }
  }
}
