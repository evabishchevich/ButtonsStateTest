package com.github.evabishchevich.redux

import com.github.evabishchevich.ClearResultOrError
import com.github.evabishchevich.OnLoadingError
import com.github.evabishchevich.OnLoadingResult
import com.github.evabishchevich.StartLoading
import com.github.evabishchevich.StateChangingEvent
import com.github.evabishchevich.state.ButtonsVisibility

fun ButtonsVisibility.reduceButtonsVisibilityRedux(event: StateChangingEvent): ButtonsVisibility {
    return ButtonsVisibility(
        b1Visible.reduceVisibility(event),
        b2Visible.reduceVisibility(event),
        b3Visible.reduceVisibility(event),
        b4Visible.reduceVisibility(event),
    )
}

private fun Boolean.reduceVisibility(event: StateChangingEvent): Boolean {
    return when (event) {
        is StartLoading -> false
        is OnLoadingError,
        is OnLoadingResult -> true
        is ClearResultOrError -> this
    }
}

