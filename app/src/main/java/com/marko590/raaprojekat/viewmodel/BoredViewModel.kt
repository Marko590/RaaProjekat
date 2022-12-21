package com.marko590.raaprojekat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marko590.raaprojekat.model.*
import kotlinx.coroutines.launch


class BoredViewModel: ViewModel() {


    val uiTextLiveData = MutableLiveData<ApiActivity>()

     fun getUpdatedText() {
         viewModelScope.launch{
             val updatedActivity = fetchActivity()
             uiTextLiveData.postValue(updatedActivity)
         }
    }

    suspend fun fetchActivity():ApiActivity{

        val instance= BoredRetrofitClient.getInstance()
        val api=instance.create(BoredApi::class.java)

        val response=api.getActivity()
        var retVal:ApiActivity= ApiActivity("","",0,0f,"",0,0f)
        if(response.isSuccessful){
            retVal.activity=response.body()?.activity!!

        }
        return ApiActivity(response.body()?.activity!!,
            response.body()?.type!!,
            response.body()?.participants!!,
            response.body()?.price!!,
            response.body()?.link!!,
            response.body()?.key!!,
            response.body()?.accessibility!!)
    }
}