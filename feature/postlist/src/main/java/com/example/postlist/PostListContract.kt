package com.example.postlist

import com.alirezamp.posts.model.Post
import com.example.designsystem.UniDirectionalViewModel

interface PostListContract :
    UniDirectionalViewModel<PostListContract.Event, PostListContract.State> {

    data class State(
        val posts: List<Post> = emptyList(),
        val refreshing: Boolean = false,
    )

    sealed class Event {
        object onRefresh : Event()
    }


}