package com.github.evabishchevich

import kotlinx.coroutines.flow.Flow

class ButtonsViewModel(buttonsStateStore: ButtonsStateStore) {

    val buttonsVisibility: Flow<ButtonsVisibility> = buttonsStateStore.visibilityAsData
    val buttonsPaintingState: Flow<ButtonsPaintingState> = buttonsStateStore.paintingState

    val combinedState: Flow<CombinedButtonsState> = buttonsStateStore.combinedState
}
