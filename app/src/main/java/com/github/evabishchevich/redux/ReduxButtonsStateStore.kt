package com.github.evabishchevich.redux

import com.github.evabishchevich.state.ButtonState
import com.github.evabishchevich.state.CombinedButtonsState
import com.github.evabishchevich.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ReduxButtonsStateStore {

    private var internalCombinedState = MutableStateFlow(
        CombinedButtonsState(
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
        )
    )
    val combinedState: Flow<CombinedButtonsState> = internalCombinedState

    fun onEvent(event: Event) {
        val newState = internalCombinedState.value.reduceCombinedStateRedux(event)
        internalCombinedState.value = newState
    }
}
