package com.github.evabishchevich

sealed class Message

sealed class StateChangingMessage : Message()

object StartLoading : StateChangingMessage()
object OnLoadingResult : StateChangingMessage()
object OnLoadingError : StateChangingMessage()
object ClearResultOrError : StateChangingMessage()

sealed class ButtonMessage : Message()

object Btn1Clicked : ButtonMessage()
object Btn2Clicked : ButtonMessage()
object Btn3Clicked : ButtonMessage()
object Btn4Clicked : ButtonMessage()

