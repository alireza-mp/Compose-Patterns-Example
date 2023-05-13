package com.alirezamp.posts_local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alirezamp.posts_local.dto.PostLocalDto
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDao {

    @Query("SELECT * FROM post_tb")
    fun getPosts(): Flow<List<PostLocalDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(posts: List<PostLocalDto>)

}