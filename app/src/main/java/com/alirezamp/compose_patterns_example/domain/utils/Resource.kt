package com.alirezamp.compose_patterns_example.domain.utils

sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val message: String, val data: T) : Resource<T>()

}