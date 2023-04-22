package com.alirezamp.compose_patterns_example.di

import com.alirezamp.compose_patterns_example.presentation.home_screen.HomeScreenViewDataMapper
import com.alirezamp.compose_patterns_example.presentation.home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        HomeScreenViewDataMapper()
    }
    viewModel {
        HomeViewModel(get(), get())
    }

}