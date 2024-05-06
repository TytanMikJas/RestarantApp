package com.example.androidapp.Client.Menu

import java.util.Locale.Category

data class MenuItemDto(
    val id: Int,
    val name: String,
    val description: String,
    val allergens: List<String>,
    val price: Float,
    val category: Category
)