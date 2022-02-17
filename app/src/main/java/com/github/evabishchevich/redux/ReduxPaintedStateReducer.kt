package com.github.evabishchevich.redux

import com.github.evabishchevich.Btn1Clicked
import com.github.evabishchevich.Btn2Clicked
import com.github.evabishchevich.Btn3Clicked
import com.github.evabishchevich.Btn4Clicked
import com.github.evabishchevich.ButtonEvent
import com.github.evabishchevich.state.ButtonsPaintedState

fun ButtonsPaintedState.reduceButtonsPaintedStateRedux(event: ButtonEvent): ButtonsPaintedState {
    return when (event) {
        Btn1Clicked -> ButtonsPaintedState(true, false, false, false)
        Btn2Clicked -> ButtonsPaintedState(false, true, false, false)
        Btn3Clicked -> ButtonsPaintedState(false, false, true, false)
        Btn4Clicked -> ButtonsPaintedState(false, false, false, true)
    }
}
