package com.alirezamp.compose_patterns_example.presentation.home_screen

import com.alirezamp.compose_patterns_example.domain.model.Post

class HomeScreenViewDataMapper {

    fun buildScreen(posts: List<Post>): List<HomeScreenViewDataModel> {
        val viewData = mutableListOf<HomeScreenViewDataModel>()
        viewData.add(HomeScreenViewDataModel.PressMeButton)
        viewData.addAll(posts.map { post -> HomeScreenViewDataModel.PostItem(post.title) })
        return viewData
    }

}