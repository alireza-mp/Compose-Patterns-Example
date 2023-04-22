package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.domain.usecase.GetPostsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetPostsUseCase(get())
    }
}