package com.alirezamp.compose_patterns_example.presentation.home_screen

sealed class HomeScreenViewDataModel {

    object PressMeButton : HomeScreenViewDataModel()

    data class PostItem(val title: String) : HomeScreenViewDataModel()

}