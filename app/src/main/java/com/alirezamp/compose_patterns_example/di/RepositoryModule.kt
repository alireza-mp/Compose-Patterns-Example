package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.repository_impl.PostRepository
import com.alirezamp.compose_patterns_example.domain.repository.IPostRepository
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