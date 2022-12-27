package com.marko590.raaprojekat.model.models.restaurants

import com.google.gson.annotations.SerializedName


data class AttributionUrl (

    @SerializedName("url"       ) var url      : String? = null,
    @SerializedName("source_id" ) var sourceId : String? = null

)