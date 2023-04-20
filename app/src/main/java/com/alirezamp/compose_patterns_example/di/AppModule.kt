package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.api.PostService
import com.alirezamp.compose_patterns_example.data.datasource_impl.post.PostLocalDatasource
import com.alirezamp.compose_patterns_example.data.datasource_impl.post.PostRemoteDatasource
import com.alirezamp.compose_patterns_example.data.db.dao.PostDao
import com.alirezamp.compose_patterns_example.data.repository_impl.PostRepository
import com.alirezamp.compose_patterns_example.domain.repository.IPostRepository
import com.alirezamp.compose_patterns_example.utils.OnlineChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostRemoteDatasource(postService: PostService): PostRemoteDatasource {
        return PostRemoteDatasource(postService)
    }

    @Provides
    @Singleton
    fun providePostLocalDatasource(postDao: PostDao): PostLocalDatasource {
        return PostLocalDatasource(postDao)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        postLocalDatasource: PostLocalDatasource,
        postRemoteDatasource: PostRemoteDatasource,
        onlineChecker: OnlineChecker,
    ): IPostRepository {
        return PostRepository(
            postRemoteDatasource = postRemoteDatasource,
            postLocalDatasource = postLocalDatasource,
            onlineChecker = onlineChecker,
        )
    }

}