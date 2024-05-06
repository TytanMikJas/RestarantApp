package com.example.androidapp.api.dto

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASEURL = "http://srv21.mikr.us:30437/"

object RetrofitInstance {
    val api: ApiService by lazy{
            Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(OkHttpClient.Builder().also { client ->
                    client.readTimeout(30, TimeUnit.SECONDS)
                    client.connectTimeout(30, TimeUnit.SECONDS)
                }.build())
                .build()
                .create(ApiService::class.java)
    }
}