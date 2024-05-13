package com.example.androidapp.api.dto

data class MenuDto (
    val id: Long,
    val name: String,
    val description: String,
    val alergens: List<String>,
    val price: Double,
    val category: Category,
    val video: String?,
    var images: List<String>
)