package com.marko590.raaprojekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.*
import com.marko590.raaprojekat.model.models.geocoding.GeocodingResponse
import com.marko590.raaprojekat.model.GeocodingRetrofitClient
import kotlinx.coroutines.launch


@Suppress("UNNECESSARY_NOT_NULL_ASSERTION")
class DetailsViewModel: ViewModel() {


    val addressLiveData = MutableLiveData<GeocodingResponse>()

    fun getUpdatedAddress(format:String,lat:Double,lng:Double,zoom:Int,addressdetails:Int) {
        viewModelScope.launch{
            val updatedActivity = fetchAddress(format,lat,lng,zoom,addressdetails)
            if(updatedActivity!=null) {
                addressLiveData.postValue(updatedActivity!!)
            }
        }
    }

    private suspend fun fetchAddress(format:String,lat:Double,lng:Double,zoom:Int,addressdetails:Int): GeocodingResponse?{

        val instance= GeocodingRetrofitClient.getInstance()
        val api=instance.create(GeocodingApi::class.java)
        val response=api.getAddress(format,lat,lng,zoom,addressdetails)

        if(response.isSuccessful){
            val body=response.body()!!
            return GeocodingResponse(
                body.placeId,
                body.licence,
                body.osmType,
                body.osmId,
                body.lat,
                body.lon,
                body.displayName,
                body.address,
                body.boundingbox
            )
        }
        return null
    }
}