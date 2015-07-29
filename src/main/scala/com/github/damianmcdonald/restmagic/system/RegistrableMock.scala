package com.github.damianmcdonald.restmagic.system

import com.github.damianmcdonald.restmagic.configurators.RootApiConfig

trait RegistrableMock {
  def getApiConfig: List[RootApiConfig]
}

class RegisteredApiMock(httpMethod: String, apiPath: String, produces: String)