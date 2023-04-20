package com.alirezamp.compose_patterns_example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alirezamp.compose_patterns_example.data.db.dao.PostDao
import com.alirezamp.compose_patterns_example.data.db.entity.PostLocalDto


@Database(entities = [PostLocalDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
}