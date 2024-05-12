package com.example.androidapp.api.dto

import java.util.Date

data class OrderDto (
    val id: Int,
    val tableId: Int,
    val status: Status,
    val createdAt: Date,
    val items: List<OrderItemDto>
)