package com.github.evabishchevich

import com.github.evabishchevich.fsm.GeneralStateStore
import com.github.evabishchevich.fsm.GeneralStateWrappingButtonsStateStore
import com.github.evabishchevich.redux.ReduxButtonsStateStore
import com.github.evabishchevich.state.ButtonsPaintedState
import com.github.evabishchevich.state.ButtonsVisibility
import com.github.evabishchevich.state.CombinedButtonsState
import kotlinx.coroutines.flow.Flow

class ButtonsViewModel(
    private val generalStateStore: GeneralStateStore,
    private val reduxStateStore: ReduxButtonsStateStore,
    private val fsmStateStore: GeneralStateWrappingButtonsStateStore,
) {

    val buttonsVisibility: Flow<ButtonsVisibility> = fsmStateStore.visibilityFromState
    val buttonsPaintedState: Flow<ButtonsPaintedState> = fsmStateStore.paintedState

    val fsmWrappedState: Flow<CombinedButtonsState> = fsmStateStore.combinedState
    val reduxCalculatedState: Flow<CombinedButtonsState> = reduxStateStore.combinedState

    fun onButtonClicked(event: ButtonEvent) {
        fsmStateStore.onButtonEvent(event)
        reduxStateStore.onEvent(event)
    }

    fun onControlClicked(event: StateChangingEvent) {
        generalStateStore.onEvent(event)
        reduxStateStore.onEvent(event)
    }
}
