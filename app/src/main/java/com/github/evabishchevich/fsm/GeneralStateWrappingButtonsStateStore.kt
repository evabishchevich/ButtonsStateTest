package com.github.evabishchevich.fsm

import com.github.evabishchevich.ButtonEvent
import com.github.evabishchevich.redux.reduceButtonsPaintedStateRedux
import com.github.evabishchevich.state.ButtonState
import com.github.evabishchevich.state.ButtonsPaintedState
import com.github.evabishchevich.state.ButtonsVisibility
import com.github.evabishchevich.state.CombinedButtonsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GeneralStateWrappingButtonsStateStore(
    generalStateStore: GeneralStateStore
) {

    val visibilityFromState: Flow<ButtonsVisibility> = generalStateStore.state.map {
        if (it is Loading) {
            ButtonsVisibility(false, false, false, false)
        } else {
            ButtonsVisibility(true, true, true, true)
        }
    }
    private var internalPaintingState = MutableStateFlow(ButtonsPaintedState(false, false, false, false))
    val paintedState: Flow<ButtonsPaintedState> = internalPaintingState

    private val clearPaintingsEffect = generalStateStore.effects.map { it is ClearAllPaintingsEffect }

    val combinedState: Flow<CombinedButtonsState> = combine(
        visibilityFromState,
        internalPaintingState,
        clearPaintingsEffect
    ) { visibility, paintedState, clearPaintings ->
        CombinedButtonsState(
            ButtonState(visibility.b1Visible, calculateIfPainted(clearPaintings, paintedState.b1Painted)),
            ButtonState(visibility.b2Visible, calculateIfPainted(clearPaintings, paintedState.b2Painted)),
            ButtonState(visibility.b3Visible, calculateIfPainted(clearPaintings, paintedState.b3Painted)),
            ButtonState(visibility.b4Visible, calculateIfPainted(clearPaintings, paintedState.b4Painted)),
        )
    }

    private fun calculateIfPainted(clearPaintings: Boolean, painted: Boolean): Boolean {
        return if (clearPaintings) false else painted
    }

    fun onButtonEvent(event: ButtonEvent) {
        val newState = internalPaintingState.value.reduceButtonsPaintedStateRedux(event)
        internalPaintingState.value = newState
    }
}
