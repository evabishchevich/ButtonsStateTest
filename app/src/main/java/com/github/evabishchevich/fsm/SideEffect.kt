package com.github.evabishchevich.fsm

sealed class SideEffect

object NoEffect : SideEffect()

object ClearAllPaintingsEffect : SideEffect()
