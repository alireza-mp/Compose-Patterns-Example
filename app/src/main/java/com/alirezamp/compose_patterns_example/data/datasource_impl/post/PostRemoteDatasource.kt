package com.alirezamp.compose_patterns_example.data.datasource_impl.post

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.data.mappers.mapToDomainModel
import com.alirezamp.compose_patterns_example.data.remote.PostService
import com.alirezamp.compose_patterns_example.domain.model.Post
import com.alirezamp.compose_patterns_example.utils.ApiException
import javax.inject.Inject

class PostRemoteDatasource
@Inject
constructor(
    private val postService: PostService,
) : IPostDatasource {
    override suspend fun getPosts(): List<Post> {
        return try {
            val response = postService.getPosts()
            if (response.isSuccessful) {
                response.body()?.map { it.mapToDomainModel() } ?: listOf()
            } else {
                throw ApiException("api error", statusCode = response.code())
            }
        } catch (e: Exception) {
            throw ApiException("check your internet connection!")
        }
    }

    override suspend fun savePosts(posts: List<Post>) {
        TODO("this not used here. this used from local datasource for save posts to db")
    }
}