package com.example.postlist

import androidx.lifecycle.viewModelScope
import com.alirezamp.posts.usecase.GetPostsUseCase
import com.alirezamp.posts.usecase.UpdatePostsUseCase
import com.example.designsystem.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel
@Inject
constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val updatePostsUseCase: UpdatePostsUseCase,
) : BaseViewModel(), PostListContract {

    private val mutableState = MutableStateFlow(PostListContract.State())
    override val state: StateFlow<PostListContract.State> = mutableState.asStateFlow()

    private val _toastState = MutableSharedFlow<String>()
    val toastState: SharedFlow<String> = _toastState.asSharedFlow()

    init {
        getData()
    }

    private fun getData(isRefreshIng: Boolean = false) {
        viewModelScope.launch {

            if (isRefreshIng) {
                mutableState.update {
                    it.copy(refreshing = true)
                }
            } else observePostList()

            if (updatePostsUseCase())
                _toastState.emit("100  items added")
            else
                _toastState.emit("error")
        }
    }

    private fun observePostList() = getPostsUseCase()
        .catch {
            //
        }
        .onEach { result ->
            mutableState.update {
                it.copy(
                    posts = result,
                    refreshing = false
                )
            }
        }
        .launchIn(viewModelScope)

    override fun event(event: PostListContract.Event) = when (event) {
        is PostListContract.Event.onRefresh -> {
            getData(isRefreshIng = true)
        }
    }

}