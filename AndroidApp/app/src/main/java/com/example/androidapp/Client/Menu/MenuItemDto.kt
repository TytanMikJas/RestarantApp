package com.example.androidapp.Client.Menu

data class MenuItemDto(
    val id: Int,
    val name: String,
    val description: String,
    val allergens: List<String>,
    val price: Float,
    val images: List<String>,
    val video: String,
    val categoryName: String
)