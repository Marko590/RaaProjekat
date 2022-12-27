package com.marko590.raaprojekat.model

import com.marko590.raaprojekat.model.models.geocoding.GeocodingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingApi {

    @GET("reverse")
    suspend fun getAddress(
        @Query("format") format: String,
        @Query("lat") lat: Double,
        @Query("lon") lng: Double,
        @Query("zoom") zoom: Int,
        @Query("addressdetails") addressdetails: Int,


    ): Response<GeocodingResponse>
}