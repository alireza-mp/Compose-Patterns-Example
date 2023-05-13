package com.example.designsystem.base

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.designsystem.collectInLaunchEffect
import com.example.designsystem.useBase
import com.example.designsystem.widget.ErrorView
import com.example.designsystem.widget.LoadingView

@Composable
fun BaseRoute(
    baseViewModel: BaseViewModel,
    content: @Composable () -> Unit,
) {
    val (baseState, baseEffect, BaseEvent) = useBase(baseViewModel)

    val context = LocalContext.current
    val activity = context as? Activity

    baseEffect.collectInLaunchEffect { effect ->
        when (effect) {
            is BaseContract.BaseEffect.OnBackPressed -> {
                activity?.onBackPressed()
            }
        }
    }

    BaseScreen(
        baseState,
        content
    )

}

@Composable
fun BaseScreen(
    baseState: BaseContract.BaseState,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (baseState) {
            BaseContract.BaseState.OnLoading -> {
                LoadingView(modifier = Modifier.fillMaxSize())
            }

            BaseContract.BaseState.OnLoadingDialog -> TODO()
            is BaseContract.BaseState.OnError -> {
                ErrorView(errorMessage = baseState.errorMessage)
            }

            BaseContract.BaseState.OnSuccess -> content()
        }
    }
}
