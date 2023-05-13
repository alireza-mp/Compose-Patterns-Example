@file:OptIn(ExperimentalMaterialApi::class)

package com.example.postlist

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.designsystem.use
import com.example.postlist.components.PostListItem

@Composable
fun PostListRoute(
    viewModel: PostListViewModel = hiltViewModel(),
) {
    val (state, event) = use(viewModel)
    val context = LocalContext.current
    PostListScreen(
        postListState = state,
        onRefresh = {
            event.invoke(PostListContract.Event.onRefresh)
        }
    )
    LaunchedEffect(Unit) {
        viewModel.toastState.collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
private fun PostListScreen(
    postListState: PostListContract.State,
    onRefresh: () -> Unit,
) {
    val refreshState =
        rememberPullRefreshState(refreshing = postListState.refreshing, onRefresh = onRefresh)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState)
    ) {
        AnimatedVisibility(
            visible = !postListState.refreshing,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(postListState.posts) { post ->
                    PostListItem(
                        post = post,
                        onItemClick = {
                            // handle with navigation to next page
                        }
                    )
                }
            }
        }
        PullRefreshIndicator(
            postListState.refreshing,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}