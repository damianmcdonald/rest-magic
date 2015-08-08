package com.github.damianmcdonald.restmagic.services

import org.specs2.mutable.Specification
import com.github.damianmcdonald.restmagic.api.RestMagicApi
import net.liftweb.json._
import spray.testkit.Specs2RouteTest
import spray.http._
import java.io.File

class ApiRegistryServiceSpec extends Specification with Specs2RouteTest with RestMagicApi {

  def actorRefFactory = system

  private val rootApiPath = "restmagic"

  implicit val formats = net.liftweb.json.DefaultFormats

  "The ApiRegistryService" should {
    "return a json object for a GET request to path /" + rootApiPath + "/api/registry" in {
      Get("/" + rootApiPath + "/api/registry") ~> routes ~> check {
        status mustEqual StatusCodes.OK
        val response = responseAs[String]
        response must not be empty
        val json = parse(response)
        val xs = for { JObject(x) <- (json \ "apis") } yield x
        xs must not be empty
      }
    }
  }

}