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

    private var internalPaintingState = MutableStateFlow(ButtonsPaintingState(false, false, false, false))
    val paintingState: Flow<ButtonsPaintingState> = internalPaintingState

    private var internalCombinedState = MutableStateFlow(
        CombinedButtonsState(
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
        )
    )
    val combinedState: Flow<CombinedButtonsState> = internalCombinedState

    fun onMsg(msg: Message) {
        if (msg is StateChangingMessage) {
            onStateMsg(msg)
        }
        onBtnMsg(msg)

        val newState = internalCombinedState.value.reduceButtonsState(msg)
        internalCombinedState.value = newState
    }

    private fun onStateMsg(message: StateChangingMessage) {
        val newState = visibilityHolder.value.reduceButtonsVisibilityAsData(message)
        visibilityHolder.value = newState
    }

    // mix of messages as all clicks + (Result|Error -> Idle) event should be processed
    private fun onBtnMsg(message: Message) {
        val newState = internalPaintingState.value.reduceButtonsPaintingAsData(message)
        internalPaintingState.value = newState
    }
}
