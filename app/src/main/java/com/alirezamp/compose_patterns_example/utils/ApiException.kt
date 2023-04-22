package com.alirezamp.compose_patterns_example.utils

class ApiException(message: String, val statusCode: Int? = null) : Exception(message)