package com.github.evabishchevich

import com.github.evabishchevich.fsm.GeneralStateStore
import com.github.evabishchevich.redux.ReduxButtonsStateStore
import kotlinx.coroutines.delay

class LoadUseCase(
    private val generalStateStore: GeneralStateStore,
    private val reduxStateStore: ReduxButtonsStateStore
) {

    suspend operator fun invoke() {
        generalStateStore.onEvent(StartLoading)
        reduxStateStore.onEvent(StartLoading)

        delay(1500)

        val msg = if (System.currentTimeMillis() % 2 == 0L) {
            OnLoadingResult
        } else {
            OnLoadingError
        }

        generalStateStore.onEvent(msg)
        reduxStateStore.onEvent(msg)
    }
}
