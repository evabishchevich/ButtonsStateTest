package com.github.evabishchevich.redux

import com.github.evabishchevich.Btn1Clicked
import com.github.evabishchevich.Btn2Clicked
import com.github.evabishchevich.Btn3Clicked
import com.github.evabishchevich.Btn4Clicked
import com.github.evabishchevich.ClearResultOrError
import com.github.evabishchevich.Event
import com.github.evabishchevich.OnLoadingError
import com.github.evabishchevich.OnLoadingResult
import com.github.evabishchevich.StartLoading
import com.github.evabishchevich.state.ButtonState
import com.github.evabishchevich.state.CombinedButtonsState

fun CombinedButtonsState.reduceCombinedStateRedux(msg: Event): CombinedButtonsState {
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
