package com.marko590.raaprojekat.model.models.restaurants

import com.google.gson.annotations.SerializedName


data class Images (

    @SerializedName("sizes"       ) var sizes       : Sizes?       = Sizes(),
    @SerializedName("caption"     ) var caption     : String?      = null,
    @SerializedName("source_id"   ) var sourceId    : String?      = null,
    @SerializedName("source_url"  ) var sourceUrl   : String?      = null,
    @SerializedName("attribution" ) var attribution : Attribution? = Attribution()

)