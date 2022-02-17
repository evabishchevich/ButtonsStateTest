package com.github.evabishchevich

sealed class Event

sealed class StateChangingEvent : Event()

object StartLoading : StateChangingEvent()
object OnLoadingResult : StateChangingEvent()
object OnLoadingError : StateChangingEvent()
object ClearResultOrError : StateChangingEvent()

sealed class ButtonEvent : Event()

object Btn1Clicked : ButtonEvent()
object Btn2Clicked : ButtonEvent()
object Btn3Clicked : ButtonEvent()
object Btn4Clicked : ButtonEvent()

