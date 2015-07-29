package com.github.damianmcdonald.restmagic.exceptions

import spray.http.StatusCode

class HttpParameterMissingException(msg: String) extends Exception(msg)
class MethodNotSupportedException(msg: String) extends Exception(msg)