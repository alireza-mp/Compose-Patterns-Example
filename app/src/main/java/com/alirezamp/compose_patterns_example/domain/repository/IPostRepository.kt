package com.alirezamp.compose_patterns_example.domain.repository

import com.alirezamp.compose_patterns_example.domain.model.Post
import com.alirezamp.compose_patterns_example.domain.utils.Resource

interface IPostRepository {

    suspend fun getPosts(): Resource<List<Post>>

}