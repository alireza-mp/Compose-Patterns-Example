package com.alirezamp.posts_local.di

import android.app.Application
import androidx.room.Room
import com.alirezamp.posts_local.database.AppDatabase
import com.alirezamp.posts_local.database.PostDao
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
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao {
        return db.getPostDao()
    }

}