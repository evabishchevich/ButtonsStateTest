package com.github.evabishchevich

class CombinedButtonsState(
    val b1State: ButtonState,
    val b2State: ButtonState,
    val b3State: ButtonState,
    val b4State: ButtonState,
)

class ButtonState(
    val visible: Boolean,
    val painted: Boolean
)

fun CombinedButtonsState.reduceButtonsState(msg: Message): CombinedButtonsState {
    return when (msg) {
        StartLoading -> CombinedButtonsState(
            ButtonState(false, b1State.painted),
            ButtonState(false, b2State.painted),
            ButtonState(false, b3State.painted),
            ButtonState(false, b4State.painted),
        )
        OnLoadingResult,
        OnLoadingError -> CombinedButtonsState(
            ButtonState(true, b1State.painted),
            ButtonState(true, b2State.painted),
            ButtonState(true, b3State.painted),
            ButtonState(true, b4State.painted),
        )
        ClearResultOrError -> CombinedButtonsState(
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
        )
        Btn1Clicked -> CombinedButtonsState(
            ButtonState(true, true),
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
        )
        Btn2Clicked -> CombinedButtonsState(
            ButtonState(true, false),
            ButtonState(true, true),
            ButtonState(true, false),
            ButtonState(true, false),
        )
        Btn3Clicked -> CombinedButtonsState(
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, true),
            ButtonState(true, false),
        )
        Btn4Clicked -> CombinedButtonsState(
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, false),
            ButtonState(true, true),
        )
    }
}
