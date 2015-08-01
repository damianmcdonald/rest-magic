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

package com.github.damianmcdonald.restmagic.configurators

import scala.annotation.tailrec
import scala.util.matching.Regex

import shapeless.HList
import shapeless.HNil
import spray.http.StatusCode
import spray.routing.Directives._

trait RootApiConfig

class RegisteredApi(
    displayName: String,
    displayUrl: String,
    httpMethod: String,
    produces: String,
    dataMode: String,
    serveMode: String,
    responseData: Map[String, String],
    apiType: String,
    paramName: Option[String] = None
) {

  override def toString: String = {
    s"""
    |displayName: $displayName
    |displayUrl: $displayUrl
    |httpMethod: $httpMethod 
    |produces: $produces
    |dataMode: $dataMode 
    |serverMode: $serveMode 
    |responseData: $responseData
    |apiType: $apiType
    |paramName: $paramName
    """.stripMargin
  }

}

case class ErrorCode(errorCode: StatusCode, errorMessage: String)

object ServeMode {
  sealed trait ServeModeType
  case class Singular() extends ServeModeType
  case class Random() extends ServeModeType
  case class ByParam() extends ServeModeType
}

object DataMode {
  sealed trait DataModeType
  case class Inline() extends DataModeType
  case class FileStub() extends DataModeType
}

object FormMode {
  sealed trait FormModeType
  case class ByQueryString() extends FormModeType
  case class ByFormData() extends FormModeType
}

object BinaryMode {
  sealed trait BinaryModeType
  case class Inline() extends BinaryModeType
  case class Attachment() extends BinaryModeType
}
