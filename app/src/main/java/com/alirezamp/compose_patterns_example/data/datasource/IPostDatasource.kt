package com.alirezamp.compose_patterns_example.data.datasource

import com.alirezamp.compose_patterns_example.domain.model.Post

interface IPostDatasource {

    suspend fun getPosts(): List<Post>

}