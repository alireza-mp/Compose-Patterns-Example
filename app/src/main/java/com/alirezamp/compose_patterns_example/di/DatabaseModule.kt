package com.alirezamp.compose_patterns_example.di

import android.app.Application
import androidx.room.Room
import com.alirezamp.compose_patterns_example.data.db.AppDatabase
import com.alirezamp.compose_patterns_example.data.db.dao.PostDao
import com.alirezamp.compose_patterns_example.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomInstance(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Constants.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao {
        return db.getPostDao()
    }

}