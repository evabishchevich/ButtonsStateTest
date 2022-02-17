package com.github.evabishchevich

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class ButtonsStateStore(
    private val stateHolder: StateHolder
) {

    val visibilityFromState: Flow<ButtonsVisibility> =
        stateHolder.state.map { reduceButtonsVisibilityFromState(it) }

    private var visibilityHolder = MutableStateFlow(ButtonsVisibility(true, true, true, true))
    val visibilityAsData: Flow<ButtonsVisibility> = visibilityHolder

    fun onMsg(message: StateChangingMessage) {
        val newState = visibilityHolder.value.reduceButtonsVisibilityAsData(message)

        visibilityHolder.value = newState
    }
}
