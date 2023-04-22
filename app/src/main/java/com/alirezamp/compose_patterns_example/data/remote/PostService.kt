package com.alirezamp.compose_patterns_example.data.remote

import com.alirezamp.compose_patterns_example.data.remote.model.PostRemoteDto
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostRemoteDto>>

}