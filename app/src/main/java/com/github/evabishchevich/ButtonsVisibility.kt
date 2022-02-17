package com.github.evabishchevich

class ButtonsVisibility(
    val b1Visible: Boolean,
    val b2Visible: Boolean,
    val b3Visible: Boolean,
    val b4Visible: Boolean,
)

fun reduceButtonsVisibilityFromState(state: GeneralState): ButtonsVisibility {
    return if (state is Loading) {
        ButtonsVisibility(false, false, false, false)
    } else {
        ButtonsVisibility(true, true, true, true)
    }
}

fun ButtonsVisibility.reduceButtonsVisibilityAsData(msg: StateChangingMessage): ButtonsVisibility {
    return ButtonsVisibility(
        b1Visible.reduceVis(msg),
        b2Visible.reduceVis(msg),
        b3Visible.reduceVis(msg),
        b4Visible.reduceVis(msg),
    )
}

private fun Boolean.reduceVis(msg: StateChangingMessage): Boolean {
    return when (msg) {
        is StartLoading -> false
        is OnLoadingError,
        is OnLoadingResult -> true
        is ClearResultOrError -> this
    }
}
