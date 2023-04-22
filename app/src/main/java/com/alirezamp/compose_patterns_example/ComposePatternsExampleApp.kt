package com.alirezamp.compose_patterns_example

import android.app.Application
import com.alirezamp.compose_patterns_example.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComposePatternsExampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComposePatternsExampleApp)
            modules(
                databaseModule,
                datasourceModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
            )
        }
    }

}