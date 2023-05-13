package com.alirezamp.posts_repository.repository

import com.alirezamp.posts.model.Post
import com.alirezamp.posts.repository.IPostRepository
import com.alirezamp.posts_local.database.PostDao
import com.alirezamp.posts_local.mapper.toPost
import com.alirezamp.posts_local.mapper.toPostLocalDto
import com.alirezamp.posts_remote.PostService
import com.alirezamp.posts_remote.mapper.toPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostRepository
@Inject
constructor(
    private val postDao: PostDao,
    private val postService: PostService,
) : IPostRepository {

    override fun getPosts(): Flow<List<Post>> {
        return postDao.getPosts().map { list -> list.map { it.toPost() } }
    }

    override suspend fun updatePosts(): Boolean {
        return try {
            val posts = postService.getPosts().map { it.toPost().toPostLocalDto() }
            postDao.savePosts(posts)
            true
        } catch (e: Exception) {
            false
        }
    }
}

