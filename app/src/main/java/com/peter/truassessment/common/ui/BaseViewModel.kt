package com.peter.truassessment.common.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel  <STATE: State, INTENT: Intent>: ViewModel() {
    private val _screenState by lazy { MutableStateFlow(initialState()) }
    val screenState: StateFlow<STATE> = _screenState.asStateFlow()


    abstract fun initialState(): STATE
    abstract fun handleIntent(intent: INTENT)
    protected fun updateState(reducer: (STATE) -> STATE) {
        _screenState.update(reducer)
    }

}