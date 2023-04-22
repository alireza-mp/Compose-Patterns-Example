package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.datasource.IPostDatasource
import com.alirezamp.compose_patterns_example.data.datasource_impl.post.PostLocalDatasource
import com.alirezamp.compose_patterns_example.data.datasource_impl.post.PostRemoteDatasource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val datasourceModule = module {
    single<IPostDatasource>(qualifier = named("PostRemoteDatasource")) {
        PostRemoteDatasource(get())
    }
    single<IPostDatasource>(qualifier = named("PostLocalDatasource")) {
        PostLocalDatasource(get())
    }
}