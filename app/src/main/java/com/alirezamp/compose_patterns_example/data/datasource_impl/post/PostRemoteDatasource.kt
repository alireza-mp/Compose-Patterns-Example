package com.alirezamp.compose_patterns_example.data.datasource_impl.post

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.data.mappers.mapToDomainModel
import com.alirezamp.compose_patterns_example.data.remote.PostService
import com.alirezamp.compose_patterns_example.domain.model.Post

class PostRemoteDatasource(
    private val postService: PostService,
) : IPostDatasource {

    override suspend fun getPosts(): List<Post> {
        try {
            val response = postService.getPosts()
            return response.map { it.mapToDomainModel() }
        } catch (e: Exception) {
            throw e // throw a ApiException
        }
    }


    override suspend fun savePosts(posts: List<Post>) {
        TODO("this not used here. this used from local datasource for save posts to db")
    }
}