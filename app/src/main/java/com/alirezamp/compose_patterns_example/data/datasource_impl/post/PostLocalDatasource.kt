package com.alirezamp.compose_patterns_example.data.datasource_impl.post

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.data.local.dao.PostDao
import com.alirezamp.compose_patterns_example.data.mappers.mapFromDomainModel
import com.alirezamp.compose_patterns_example.data.mappers.mapToDomainModel
import com.alirezamp.compose_patterns_example.domain.model.Post
import javax.inject.Inject

class PostLocalDatasource
@Inject constructor(
    private val postDao: PostDao,
) : IPostDatasource {

    override suspend fun getPosts(): List<Post> {
        return try {
            postDao.getPosts().map { it.mapToDomainModel() }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun savePosts(posts: List<Post>) {
        return try {
            postDao.savePosts(posts.map { it.mapFromDomainModel() })
        } catch (e: Exception) {
            throw e
        }
    }

}