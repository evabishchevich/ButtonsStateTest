package com.github.evabishchevich

import kotlinx.coroutines.delay

suspend fun load(stateHolder: StateHolder, buttonsStateStore: ButtonsStateStore) {
    stateHolder.onMsg(StartLoading)
    buttonsStateStore.onMsg(StartLoading)

    delay(1500)

    val msg = if (System.currentTimeMillis() % 2 == 0L) {
        OnLoadingResult
    } else {
        OnLoadingError
    }

    stateHolder.onMsg(msg)
    buttonsStateStore.onMsg(msg)
}
