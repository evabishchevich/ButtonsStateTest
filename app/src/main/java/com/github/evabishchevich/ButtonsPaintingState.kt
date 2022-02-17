package com.github.evabishchevich

class ButtonsPaintingState(
    val b1Painted: Boolean,
    val b2Painted: Boolean,
    val b3Painted: Boolean,
    val b4Painted: Boolean,
)

fun reduceButtonsPaintingFromState(state: GeneralState): ButtonsPaintingState {
    throw IllegalStateException("Not possible to figure out painting state from general state")
}

fun ButtonsPaintingState.reduceButtonsPaintingAsData(msg: Message): ButtonsPaintingState {
    return when (msg) {
        Btn1Clicked -> ButtonsPaintingState(true, false, false, false)
        Btn2Clicked -> ButtonsPaintingState(false, true, false, false)
        Btn3Clicked -> ButtonsPaintingState(false, false, true, false)
        Btn4Clicked -> ButtonsPaintingState(false, false, false, true)
        ClearResultOrError -> ButtonsPaintingState(false, false, false, false)
        else -> this
    }
}
