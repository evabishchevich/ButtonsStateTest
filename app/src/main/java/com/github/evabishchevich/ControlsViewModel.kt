package com.github.evabishchevich

import androidx.lifecycle.LifecycleCoroutineScope
import com.github.evabishchevich.fsm.GeneralStateStore
import com.github.evabishchevich.redux.ReduxButtonsStateStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ControlsViewModel(
    private val generalStateStore: GeneralStateStore,
    private val reduxStateStore: ReduxButtonsStateStore,
    private val load: LoadUseCase,
) {

    val currentState: Flow<String> = generalStateStore.state.map { it.toString() }

    fun onControlClicked(event: StateChangingEvent, scope: LifecycleCoroutineScope) {
        when (event) {
            is StartLoading -> {
                // imagine it's VM scope
                scope.launch { load() }
            }
            else -> {
                generalStateStore.onEvent(event)
                reduxStateStore.onEvent(event)
            }
        }
    }
}
