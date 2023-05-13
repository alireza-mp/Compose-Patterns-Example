package com.alirezamp.posts_remote.dto


import com.squareup.moshi.Json

data class PostRemoteDto(
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: Int,
)