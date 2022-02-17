package com.github.evabishchevich

import android.util.Log
import com.tinder.StateMachine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

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

class StateHolder(private val initialState: GeneralState) {

    private val internalState = MutableStateFlow(initialState)
    val state: Flow<GeneralState> = internalState

    private val stateMachine = StateMachine.create<GeneralState, StateChangingMessage, () -> Unit> {
        initialState(initialState)
        state<Idle> {
            on<StartLoading> { transitionTo(Loading) }
        }
        state<Loading> {
            on<OnLoadingError> { transitionTo(Error) }
            on<OnLoadingResult> { transitionTo(Result) }
        }
        state<Result> {
            on<ClearResultOrError> { transitionTo(Idle) }
        }
        state<Error> {
            on<ClearResultOrError> { transitionTo(Idle) }
        }


        onTransition { transition ->
            if (transition is StateMachine.Transition.Valid) {
                transition.apply {
                    Log.d("XXX", "Valid transition for $event from $fromState to $toState")
                }
                internalState.value = transition.toState
            } else {
                transition.apply {
                    Log.d("XXX", "Invalid transition for $event for $fromState ")
                }
            }
        }
    }

    fun onMsg(msg: StateChangingMessage) {
        stateMachine.transition(msg)
    }
}
