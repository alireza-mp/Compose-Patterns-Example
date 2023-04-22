package com.alirezamp.compose_patterns_example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alirezamp.compose_patterns_example.data.local.dao.PostDao
import com.alirezamp.compose_patterns_example.data.local.entity.PostLocalDto


@Database(entities = [PostLocalDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao
}