package com.example.androidapp.viewmodels.waiter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WaiterViewModel: ViewModel() {
    private lateinit var _state: MutableStateFlow<WaiterViewState>
    var state: StateFlow<WaiterViewState> = _state
}