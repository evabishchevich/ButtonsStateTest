package com.github.evabishchevich

import com.github.evabishchevich.fsm.GeneralStateStore
import com.github.evabishchevich.fsm.Idle
import com.github.evabishchevich.fsm.GeneralStateWrappingButtonsStateStore
import com.github.evabishchevich.redux.ReduxButtonsStateStore

private val generalStateStore = GeneralStateStore(Idle)
private val separatePropsButtonsStateStore = GeneralStateWrappingButtonsStateStore(generalStateStore)
private val combinedButtonsStateStore = ReduxButtonsStateStore()
private val loadUseCase = LoadUseCase(generalStateStore, combinedButtonsStateStore)

fun createControlsVM(): ControlsViewModel {
    return ControlsViewModel(generalStateStore, combinedButtonsStateStore, loadUseCase)
}

fun createButtonsVM(): ButtonsViewModel {
    return ButtonsViewModel(
        generalStateStore,
        combinedButtonsStateStore,
        separatePropsButtonsStateStore
    )
}
