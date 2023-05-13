package com.alirezamp.posts_repository.di

import com.alirezamp.posts.repository.IPostRepository
import com.alirezamp.posts_repository.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsPostRepository(
        postRepository: PostRepository,
    ): IPostRepository

}