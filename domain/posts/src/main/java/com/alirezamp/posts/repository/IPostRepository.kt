package com.alirezamp.posts.repository

import com.alirezamp.posts.model.Post
import kotlinx.coroutines.flow.Flow

interface IPostRepository {

    fun getPosts(): Flow<List<Post>>

    suspend fun updatePosts(): Boolean

}