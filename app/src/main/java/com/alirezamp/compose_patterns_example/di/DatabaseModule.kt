package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.data.local.model.PostLocalDto
import com.alirezamp.compose_patterns_example.utils.Constants
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.dsl.module

val databaseModule = module {
    single {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                PostLocalDto::class
            )
        )
            .name(Constants.DATABASE_NAME)
            .build()
        Realm.open(config)

    }
}