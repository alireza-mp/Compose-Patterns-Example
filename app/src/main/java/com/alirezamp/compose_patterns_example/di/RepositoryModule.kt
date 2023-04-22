package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.repository_impl.PostRepository
import com.alirezamp.compose_patterns_example.domain.repository.IPostRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<IPostRepository> {
        PostRepository(
            postRemoteDatasource = get(named("PostRemoteDatasource")),
            postLocalDatasource = get(named("PostLocalDatasource")),
            onlineChecker = get()
        )
    }
}