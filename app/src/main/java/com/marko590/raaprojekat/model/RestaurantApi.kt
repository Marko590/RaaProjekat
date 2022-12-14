package com.marko590.raaprojekat.model

import com.marko590.raaprojekat.model.models.restaurants.RestaurantsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {
    @Headers(
        "X-Triposo-Account: E9CHL2VB",
        "X-Triposo-Token: 81vio7opy5th0q8z5b3ch7whyophexp0"
    )
    @GET("api/20220411/poi.json?location_id=Paris&tag_labels=cuisine")
    suspend fun getActivity(): Response<RestaurantsResponse>

    @Headers(
        "X-Triposo-Account: E9CHL2VB",
        "X-Triposo-Token: 81vio7opy5th0q8z5b3ch7whyophexp0"
    )
    @GET("api/20220411/poi.json")
    suspend fun getFavorites(
        @Query("location_id") location_id: String,
        @Query("tag_labels") cuisine: String
    ): Response<RestaurantsResponse>
}