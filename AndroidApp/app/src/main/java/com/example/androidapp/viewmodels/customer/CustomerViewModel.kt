package com.example.androidapp.viewmodels.customer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CustomerViewModel: ViewModel() {
    private lateinit var _state: MutableStateFlow<CustomerViewState>
    var state: MutableStateFlow<CustomerViewState> = _state
}