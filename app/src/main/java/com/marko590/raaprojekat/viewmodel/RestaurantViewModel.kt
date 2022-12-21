package com.marko590.raaprojekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.*
import com.marko590.raaprojekat.model.models.RestaurantsResponse
import kotlinx.coroutines.launch


class RestaurantViewModel: ViewModel() {


    val uiTextLiveData = MutableLiveData<RestaurantsResponse>()

    fun getUpdatedText() {
        viewModelScope.launch{
            val updatedActivity = fetchActivity()
            uiTextLiveData.postValue(updatedActivity)
        }
    }

    suspend fun fetchActivity(): RestaurantsResponse {

        val instance= RestaurantRetrofitClient.getInstance()
        val api=instance.create(RestaurantApi::class.java)

        val response=api.getActivity()

        if(response.isSuccessful){
            return RestaurantsResponse(response.body()!!.results,response.body()!!.more,response.body()!!.isDeprecated)
        }
        return RestaurantsResponse(response.body()!!.results,response.body()!!.more,response.body()!!.isDeprecated)
    }
}