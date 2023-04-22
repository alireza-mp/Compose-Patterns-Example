package com.alirezamp.compose_patterns_example.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PostRemoteDto(
    val body: String,
    val title: String,
    val id: Int,
    val userId: Int,
)