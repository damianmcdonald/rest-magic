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

import com.github.damianmcdonald.configurators.DataMode.DataModeType
import com.github.damianmcdonald.configurators.ServeMode.ServeModeType
import com.github.damianmcdonald.configurators.utils.ConfiguratorUtils
import spray.http.MediaType
import spray.routing.{ Directive0, PathMatcher0 }

object SimpleRestConfig extends ConfiguratorUtils {
  def apply(
    httpMethod: Directive0,
    apiPath: PathMatcher0,
    produces: MediaType,
    dataMode: DataModeType,
    responseData: Map[String, String],
    serveMode: ServeModeType,
    validate: Boolean
  ) = {
    assert(responseData.nonEmpty, ERROR_EMPTY_MAP)
    assert(if (responseData.size == 1 && !serveMode.isInstanceOf[ServeMode.Singluar]) false else true, ERROR_SINGULAR_MODE)
    assert(if (responseData.size > 1 && serveMode.isInstanceOf[ServeMode.Singluar]) false else true, ERROR_MULTI_MODE)
    val validatedMap = if (validate) validateAndLoadResponses(dataMode, produces, responseData) else loadResponses(dataMode, responseData)
    new SimpleRestConfig(httpMethod, apiPath, produces, dataMode, validatedMap, serveMode)
  }
}

case class SimpleRestConfig(
  httpMethod: Directive0,
  apiPath: PathMatcher0,
  produces: MediaType,
  dataMode: DataModeType,
  responseData: Map[String, String],
  serveMode: ServeModeType
) extends RootApiConfig
