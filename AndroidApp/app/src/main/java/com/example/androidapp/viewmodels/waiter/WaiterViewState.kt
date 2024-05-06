package com.example.androidapp.viewmodels.waiter

import com.example.androidapp.api.dto.OrderDto

data class WaiterViewState (
    val orders: List<OrderDto>
)