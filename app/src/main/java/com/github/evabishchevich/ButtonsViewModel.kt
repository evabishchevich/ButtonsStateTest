package com.github.evabishchevich

import kotlinx.coroutines.flow.Flow

class ButtonsViewModel(buttonsStateStore: ButtonsStateStore) {

    val buttonsVisibility: Flow<ButtonsVisibility> = buttonsStateStore.visibilityAsData
}
