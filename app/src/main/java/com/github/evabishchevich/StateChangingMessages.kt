package com.github.evabishchevich

sealed class StateChangingMessage

object StartLoading : StateChangingMessage()
object OnLoadingResult : StateChangingMessage()
object OnLoadingError : StateChangingMessage()
object ClearResultOrError : StateChangingMessage()
