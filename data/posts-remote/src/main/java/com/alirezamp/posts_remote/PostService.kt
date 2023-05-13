package com.alirezamp.posts_remote

import com.alirezamp.posts_remote.dto.PostRemoteDto
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPosts(): List<PostRemoteDto>

}

internal const val BASE_URL = "https://jsonplaceholder.typicode.com/"