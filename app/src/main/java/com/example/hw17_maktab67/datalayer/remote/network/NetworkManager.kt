package com.example.hw17_maktab67.datalayer.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MovieApi = retrofit.create(MovieApi::class.java)

}