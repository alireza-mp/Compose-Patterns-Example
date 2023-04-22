package com.alirezamp.compose_patterns_example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alirezamp.compose_patterns_example.data.local.entity.PostLocalDto

@Dao
interface PostDao {

    @Query("SELECT * FROM post_tb")
    suspend fun getPosts(): List<PostLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePosts(posts: List<PostLocalDto>)

}