package com.marko590.raaprojekat.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocodingRetrofitClient {
    private const val baseUrl="https://nominatim.openstreetmap.org/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}