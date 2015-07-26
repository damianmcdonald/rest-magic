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

package com.github.damianmcdonald.configurators

import scala.util.matching.Regex

trait RootApiConfig

object ParamExtractor {
  sealed trait ExtractorType
  case class StringVal(expr: Regex = """\w+""".r) extends ExtractorType
  case class IntVal(expr: Regex = """\d+""".r) extends ExtractorType
  case class LongVal(expr: Regex = """\d+""".r) extends ExtractorType
}

object ServeMode {
  sealed trait ServeModeType
  case class Singluar() extends ServeModeType
  case class Random() extends ServeModeType
  case class ByParam() extends ServeModeType
}

object DataMode {
  sealed trait DataModeType
  case class Inline() extends DataModeType
  case class FileStub() extends DataModeType
}
