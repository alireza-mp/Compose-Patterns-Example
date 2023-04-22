package com.alirezamp.compose_patterns_example.presentation.home_screen

import androidx.compose.runtime.Immutable
import com.alirezamp.compose_patterns_example.base.UiEvent
import com.alirezamp.compose_patterns_example.base.UiState

@Immutable
sealed class HomeScreenEvent : UiEvent {
    object OnLoading : HomeScreenEvent()
    data class ShowData(
        val items: List<HomeScreenViewDataModel> = emptyList(),
        val error: String? = null,
    ) : HomeScreenEvent()

}

data class HomeScreenState(
    val isLoading: Boolean,
    val data: List<HomeScreenViewDataModel>,
    val error: String?,
) : UiState {

    companion object {
        fun initial() = HomeScreenState(
            isLoading = false,
            data = emptyList(),
            error = null,
        )
    }
}