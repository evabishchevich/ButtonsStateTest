package com.github.evabishchevich.fsm

import android.util.Log
import com.github.evabishchevich.ClearResultOrError
import com.github.evabishchevich.OnLoadingError
import com.github.evabishchevich.OnLoadingResult
import com.github.evabishchevich.StartLoading
import com.github.evabishchevich.StateChangingEvent
import com.tinder.StateMachine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class GeneralStateStore(private val initialState: GeneralState) {

    private val internalState = MutableStateFlow(initialState)
    val state: Flow<GeneralState> = internalState

    private val internalEffects = MutableStateFlow<SideEffect>(NoEffect)
    val effects: Flow<SideEffect> = internalEffects

    private val stateMachine = StateMachine.create<GeneralState, StateChangingEvent, SideEffect> {
        initialState(initialState)
        state<Idle> {
            on<StartLoading> { transitionTo(Loading) }
        }
        state<Loading> {
            on<OnLoadingError> { transitionTo(Error) }
            on<OnLoadingResult> { transitionTo(Result) }
        }
        state<Result> {
            on<ClearResultOrError> { transitionTo(Idle, ClearAllPaintingsEffect) }
        }
        state<Error> {
            on<ClearResultOrError> { transitionTo(Idle, ClearAllPaintingsEffect) }
        }

        onTransition { transition ->
            if (transition is StateMachine.Transition.Valid) {
                transition.apply {
                    Log.d("XXX", "Valid transition for $event from $fromState to $toState")
                }
                internalState.value = transition.toState
                internalEffects.value = transition.sideEffect ?: NoEffect
            } else {
                transition.apply {
                    Log.d("XXX", "Invalid transition for $event for $fromState ")
                }
            }
        }
    }

    fun onEvent(event: StateChangingEvent) {
        stateMachine.transition(event)
    }
}
