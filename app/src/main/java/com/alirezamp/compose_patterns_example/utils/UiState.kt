package com.alirezamp.compose_patterns_example.utils

sealed class UiState<out R> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error<out T>(val message: String, val data: T) : UiState<T>()
}
