package com.github.evabishchevich.fsm

sealed class GeneralState

object Idle : GeneralState() {

    override fun toString(): String = "Idle"
}

object Loading : GeneralState() {

    override fun toString(): String = "Loading"
}

object Result : GeneralState() {

    override fun toString(): String = "Result"
}

object Error : GeneralState() {

    override fun toString(): String = "Error"
}
