package com.example.androidapp.api.dto

import com.example.androidapp.Client.Menu.MenuItemDto

data class OrderItemDto (
    val id: Int,
    val menuItem: MenuItemDto,
    val quantity: Int
)
