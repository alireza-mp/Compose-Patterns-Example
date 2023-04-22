package com.alirezamp.compose_patterns_example.data.remote

import com.alirezamp.compose_patterns_example.data.remote.model.PostRemoteDto

interface PostService {

    suspend fun getPosts(): List<PostRemoteDto>

}