package com.example.postlist.components

import androidx.compose.runtime.Composable
import com.alirezamp.posts.model.Post
import com.example.designsystem.components.PostItem

@Composable
fun PostListItem(
    post: Post,
    onItemClick: () -> Unit,
) {
    with(post) {
        PostItem(
            title = title,
            onItemClick = onItemClick,
        )
    }
}