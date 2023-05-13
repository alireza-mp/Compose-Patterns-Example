package com.alirezamp.posts_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alirezamp.posts_local.dto.PostLocalDto


@Database(entities = [PostLocalDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {
        const val DATABASE_NAME = "my_db"
    }
}