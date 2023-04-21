package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.data.datasource_impl.post.PostLocalDatasource
import com.alirezamp.compose_patterns_example.data.datasource_impl.post.PostRemoteDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Binds
    @Singleton
    @Named("PostRemoteDatasource")
    abstract fun bindsPostRemoteDatasource(
        postRemoteDatasource: PostRemoteDatasource,
    ): IPostDatasource


    @Binds
    @Singleton
    @Named("PostLocalDatasource")
    abstract fun bindsPostLocalDatasource(
        postLocalDatasource: PostLocalDatasource,
    ): IPostDatasource

}