package com.alirezamp.compose_patterns_example.presentation.home_screen

import androidx.lifecycle.viewModelScope
import com.alirezamp.compose_patterns_example.base.BaseViewModel
import com.alirezamp.compose_patterns_example.base.Reducer
import com.alirezamp.compose_patterns_example.domain.usecase.GetPostsUseCase
import com.alirezamp.compose_patterns_example.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val postsUseCase: GetPostsUseCase,
    private val homeScreenViewDataMapper: HomeScreenViewDataMapper,
) : BaseViewModel<HomeScreenState, HomeScreenEvent>() {

    private val reducer = MainReducer(HomeScreenState.initial())

    override val state: StateFlow<HomeScreenState>
        get() = reducer.state

    init {
        getData()
    }

    fun onButtonClick() {
        getData()
    }


    private class MainReducer(initialState: HomeScreenState) :
        Reducer<HomeScreenState, HomeScreenEvent>(initialState) {

        override fun reduce(oldState: HomeScreenState, event: HomeScreenEvent) {
            when (event) {
                is HomeScreenEvent.ShowData -> {
                    sendState(
                        oldState.copy(
                            isLoading = false,
                            data = event.items,
                            error = event.error,
                        )
                    )
                }
                is HomeScreenEvent.OnLoading -> {
                    sendState(
                        oldState.copy(
                            isLoading = true,
                            data = emptyList(),
                            error = null,
                        )
                    )
                }
            }
        }
    }

    private fun sendEvent(event: HomeScreenEvent) {
        reducer.sendEvent(event)
    }

    /*
    * get data from local or remote db
    */
    private fun getData() {
        viewModelScope.launch {
            sendEvent(HomeScreenEvent.OnLoading)
            val data = withContext(Dispatchers.IO) {
                postsUseCase()
            }
            when (data) {
                is Resource.Success -> {
                    sendEvent(
                        HomeScreenEvent.ShowData(
                            items = homeScreenViewDataMapper.buildScreen(
                                posts = data.data,
                            )
                        )
                    )
                }
                is Resource.Error -> {
                    sendEvent(
                        HomeScreenEvent.ShowData(
                            error = data.message,
                            items = homeScreenViewDataMapper.buildScreen(posts = data.data),
                        )
                    )
                }
            }

        }
    }
}