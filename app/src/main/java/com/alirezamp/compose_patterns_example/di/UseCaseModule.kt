package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.repository_impl.PostRepository
import com.alirezamp.compose_patterns_example.domain.usecase.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPostsUseCase(
        postRepository: PostRepository,
    ): GetPostsUseCase {
        return GetPostsUseCase(postRepository)
    }

}