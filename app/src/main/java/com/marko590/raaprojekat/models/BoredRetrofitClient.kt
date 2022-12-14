package com.marko590.raaprojekat.models

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BoredRetrofitClient {
    val baseUrl="https://www.boredapi.com/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}