package com.example.designsystem.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow


open class BaseViewModel : ViewModel(), BaseContract {

    //*********************** state *********************************
    protected val mutableBaseState: MutableStateFlow<BaseContract.BaseState> =
        MutableStateFlow(BaseContract.BaseState.OnSuccess)

    override val baseState: StateFlow<BaseContract.BaseState> = mutableBaseState.asStateFlow()

    ///******************** effect **********************************
    protected val baseEffectChannel = Channel<BaseContract.BaseEffect>(Channel.UNLIMITED)

    override val baseEffect: Flow<BaseContract.BaseEffect> = baseEffectChannel.receiveAsFlow()

    ///************************** event *******************************

    override fun baseEvent(event: BaseContract.BaseEvent) = when (event) {
        BaseContract.BaseEvent.OnBackPressed -> onBackPressed()
        BaseContract.BaseEvent.OnRetryPressed -> onRetryPressed()
    }

    private fun onBackPressed() {
        baseEffectChannel.trySend(BaseContract.BaseEffect.OnBackPressed)
    }

    private fun onRetryPressed() {

    }

}