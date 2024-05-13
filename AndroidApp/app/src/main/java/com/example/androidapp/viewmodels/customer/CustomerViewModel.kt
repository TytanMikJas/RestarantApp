package com.example.androidapp.viewmodels.customer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.OrderDto
import com.example.androidapp.api.dto.RetrofitInstance
import kotlinx.coroutines.launch

class CustomerViewModel: ViewModel() {
    var menu: MutableState<List<MenuDto>> = mutableStateOf(emptyList())
    private set
    var cart: MutableState<List<MenuDto>> = mutableStateOf(emptyList())
    private set
    var order: MutableState<OrderDto?> = mutableStateOf(null)
    private set

    var selectedTabIndex by mutableIntStateOf(0)

    init {
        val apiService = RetrofitInstance.api
        viewModelScope.launch {
            var response = apiService.getMenu()
            response = response.map { it ->
                it.copy(
                    images = it.images.map {
                        "http://srv21.mikr.us:30437/public/${it}"
                    },
                    video = if(it.video.isNullOrEmpty()) null else "http://srv21.mikr.us:30437/public/${it.video}"
                )
            }
            menu.value = response
        }
    }
}