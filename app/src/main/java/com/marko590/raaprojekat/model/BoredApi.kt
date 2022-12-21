package com.marko590.raaprojekat.model

import retrofit2.Response
import retrofit2.http.GET

interface BoredApi {
    @GET("activity")
    suspend fun getActivity(): Response<ApiActivity>
}