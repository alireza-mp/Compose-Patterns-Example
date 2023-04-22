package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.remote.PostService
import com.alirezamp.compose_patterns_example.data.remote.PostServiceImpl
import com.alirezamp.compose_patterns_example.utils.ExperimentalOnlineChecker
import com.alirezamp.compose_patterns_example.utils.OnlineChecker
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    single<PostService> {
        PostServiceImpl(get())
    }

    single<Runtime> {
        Runtime.getRuntime()
    }

    single<OnlineChecker> {
        ExperimentalOnlineChecker(get(), get())
    }

}