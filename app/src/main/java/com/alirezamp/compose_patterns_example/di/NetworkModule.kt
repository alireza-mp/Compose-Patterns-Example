package com.alirezamp.compose_patterns_example.di

import android.app.Application
import com.alirezamp.compose_patterns_example.data.api.PostService
import com.alirezamp.compose_patterns_example.utils.Constants
import com.alirezamp.compose_patterns_example.utils.ExperimentalOnlineChecker
import com.alirezamp.compose_patterns_example.utils.OnlineChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        httpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(httpClient)
            // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)


    @Singleton
    @Provides
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideRountime(): Runtime {
        return Runtime.getRuntime()
    }

    @Provides
    fun provideOnlineChecker(
        context: Application,
        runtime: Runtime,
    ): OnlineChecker {
        return ExperimentalOnlineChecker(context, runtime)
    }

}