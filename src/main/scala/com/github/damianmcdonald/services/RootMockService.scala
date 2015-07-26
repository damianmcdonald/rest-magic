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

package com.github.damianmcdonald.services

import spray.routing.Route

import scala.util.Random

trait RootMockService {

  def route: Route

  private def getRandomInt(x: Int, y: Int) = {
    Random.nextInt((y - x) + 1) + x
  }

  protected def serveRandom(xs: Map[String, String]): String = {
    val index = getRandomInt(0, xs.size - 1)
    def inner(xs: Iterable[String], accum: Int): String = {
      xs match {
        case Nil => xs.headOption.getOrElse("No response data available in responseData Map").toString
        case head :: tail if (accum == index) => head
        case head :: tail => inner(tail, accum + 1)
      }
    }
    inner(xs.values, 0)
  }

  protected def serveSingular(xs: Map[String, String]): String = {
    xs.values.headOption.getOrElse("No response data available in responseData Map").toString
  }

}
