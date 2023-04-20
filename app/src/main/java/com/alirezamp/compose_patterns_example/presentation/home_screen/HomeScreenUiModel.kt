package com.alirezamp.compose_patterns_example.presentation.home_screen

import com.alirezamp.compose_patterns_example.domain.model.Post

/*
* home screen components
*/

data class HomeScreenUiModel(
    val posts: List<Post>,
    // other data
)