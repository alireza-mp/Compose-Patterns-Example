package com.alirezamp.compose_patterns_example.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alirezamp.compose_patterns_example.domain.usecase.GetPostsUseCase
import com.alirezamp.compose_patterns_example.domain.utils.Resource
import com.alirezamp.compose_patterns_example.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val postsUseCase: GetPostsUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<HomeScreenUiModel>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<HomeScreenUiModel>> get() = _uiState

    init {
        getData()
    }

    fun onButtonClick() {
        getData()
    }

    /*
   * get data from local or remote db
   */
    private fun getData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                postsUseCase()
            }
            when (data) {
                is Resource.Success -> {
                    _uiState.value = UiState.Success(
                        data = HomeScreenUiModel(
                            posts = data.data,
                        )
                    )
                }
                is Resource.Error -> {
                    _uiState.value = UiState.Error(
                        message = data.message,
                        data = HomeScreenUiModel(
                            posts = data.data,
                        )
                    )
                }
            }
        }
    }

}