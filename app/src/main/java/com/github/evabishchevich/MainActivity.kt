package com.github.evabishchevich

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

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button

    private val stateHolder = StateHolder(Idle)
    private val buttonsStore = ButtonsStateStore(stateHolder)
    private val buttonsViewModel = ButtonsViewModel(buttonsStore)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        bindButtonsVisibility()

        bindControls()
    }

    private fun bindButtonsVisibility() {
        buttonsViewModel.buttonsVisibility.observeWhenStarted(lifecycleScope) {
            btn1.isInvisible = it.b1Visible.not()
            btn2.isInvisible = it.b2Visible.not()
            btn3.isInvisible = it.b3Visible.not()
            btn4.isInvisible = it.b4Visible.not()
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
        }
    }
}

fun <T> Flow<T>.observeWhenStarted(scope: LifecycleCoroutineScope, action: suspend (value: T) -> Unit): Job {
    return scope.launchWhenStarted {
        collect(action)
    }
}
