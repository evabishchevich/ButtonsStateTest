package com.github.evabishchevich.state

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
