package com.github.evabishchevich

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val stateHolder = StateHolder(Idle)
    private val buttonsStore = ButtonsStateStore(stateHolder)
    private val buttonsViewModel = ButtonsViewModel(buttonsStore)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val separateStateButtons = listOf<Button>(
            findViewById(R.id.btn1), findViewById(R.id.btn2), findViewById(R.id.btn3), findViewById(R.id.btn4)
        )
        val combinedStateButtons = listOf<Button>(
            findViewById(R.id.btn5), findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8)
        )

        bindButtonsVisibility(separateStateButtons)
        bindButtonsPainting(separateStateButtons)
        bindCombinedStateToButtons(combinedStateButtons)
        bindButtonClicks(separateStateButtons)
        bindButtonClicks(combinedStateButtons)

        bindControls()
    }

    private fun bindButtonsVisibility(buttons: List<Button>) {
        buttonsViewModel.buttonsVisibility.observeWhenStarted(lifecycleScope) {
            buttons[0].isInvisible = it.b1Visible.not()
            buttons[1].isInvisible = it.b2Visible.not()
            buttons[2].isInvisible = it.b3Visible.not()
            buttons[3].isInvisible = it.b4Visible.not()
        }
    }

    private fun bindButtonsPainting(buttons: List<Button>) {
        buttonsViewModel.buttonsPaintingState.observeWhenStarted(lifecycleScope) {
            buttons[0].setBackgroundColor(calculateColor(it.b1Painted))
            buttons[1].setBackgroundColor(calculateColor(it.b2Painted))
            buttons[2].setBackgroundColor(calculateColor(it.b3Painted))
            buttons[3].setBackgroundColor(calculateColor(it.b4Painted))
        }
    }

    private fun bindCombinedStateToButtons(buttons: List<Button>) {
        buttonsViewModel.combinedState.observeWhenStarted(lifecycleScope) {
            buttons[0].isInvisible = it.b1State.visible.not()
            buttons[0].setBackgroundColor(calculateColor(it.b1State.painted))
            buttons[1].isInvisible = it.b2State.visible.not()
            buttons[1].setBackgroundColor(calculateColor(it.b2State.painted))
            buttons[2].isInvisible = it.b3State.visible.not()
            buttons[2].setBackgroundColor(calculateColor(it.b3State.painted))
            buttons[3].isInvisible = it.b4State.visible.not()
            buttons[3].setBackgroundColor(calculateColor(it.b4State.painted))
        }
    }

    private fun calculateColor(painted: Boolean): Int {
        return if (painted) Color.MAGENTA else Color.BLUE
    }

    private fun bindButtonClicks(buttons: List<Button>) {
        buttons[0].bindButtonClick(Btn1Clicked)
        buttons[1].bindButtonClick(Btn2Clicked)
        buttons[2].bindButtonClick(Btn3Clicked)
        buttons[3].bindButtonClick(Btn4Clicked)
    }

    private fun Button.bindButtonClick(msg: ButtonMessage) {
        setOnClickListener {
            buttonsStore.onMsg(msg)
        }
    }

    private fun bindControls() {
        val tvState = findViewById<TextView>(R.id.tvCurrentState)
        stateHolder.state.observeWhenStarted(lifecycleScope) {
            tvState.text = it.toString()
        }
        findViewById<Button>(R.id.btnLoading).setOnClickListener {
            lifecycleScope.launch {
                load(stateHolder, buttonsStore)
            }
        }
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            stateHolder.onMsg(ClearResultOrError)
            buttonsStore.onMsg(ClearResultOrError)
        }
    }
}

fun <T> Flow<T>.observeWhenStarted(scope: LifecycleCoroutineScope, action: suspend (value: T) -> Unit): Job {
    return scope.launchWhenStarted {
        collect(action)
    }
}
