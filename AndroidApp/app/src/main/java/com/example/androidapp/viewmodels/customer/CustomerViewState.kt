package com.example.androidapp.viewmodels.customer

import com.example.androidapp.api.dto.MenuDto
import com.example.androidapp.api.dto.OrderDto

data class CustomerViewState (
    val menu: List<MenuDto>,
    val cart: List<MenuDto>,
    val order: OrderDto?
)