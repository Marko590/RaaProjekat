package com.marko590.raaprojekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.*
import com.marko590.raaprojekat.model.models.RestaurantsResponse
import kotlinx.coroutines.launch


class FavoritesViewModel: ViewModel() {


    val uiTextLiveData = MutableLiveData<RestaurantsResponse>()

    fun getUpdatedFavorites(location_id:String,cuisine:String) {
        viewModelScope.launch{
            val updatedActivity = fetchFavorites(location_id, cuisine)
            uiTextLiveData.postValue(updatedActivity)
        }
    }

    private suspend fun fetchFavorites(location_id:String, cuisine:String): RestaurantsResponse {

        val instance= RestaurantRetrofitClient.getInstance()
        val api=instance.create(RestaurantApi::class.java)

        val response=api.getFavorites(location_id, cuisine)

        if(response.isSuccessful){
            return RestaurantsResponse(response.body()!!.results,response.body()!!.more,response.body()!!.isDeprecated)
        }
        return RestaurantsResponse(response.body()!!.results,response.body()!!.more,response.body()!!.isDeprecated)
    }
}