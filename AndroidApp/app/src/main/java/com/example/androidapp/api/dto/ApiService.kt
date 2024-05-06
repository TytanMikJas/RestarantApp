package com.example.androidapp.api.dto

import retrofit2.http.GET

interface ApiService {
    @GET("menu")
    suspend fun getMenu(): List<MenuDto>
}