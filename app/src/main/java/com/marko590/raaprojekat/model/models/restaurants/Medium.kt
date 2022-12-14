package com.marko590.raaprojekat.model.models.restaurants

import com.google.gson.annotations.SerializedName


data class Medium (

    @SerializedName("url"    ) var url    : String? = null,
    @SerializedName("bytes"  ) var bytes  : Int?    = null,
    @SerializedName("width"  ) var width  : Int?    = null,
    @SerializedName("format" ) var format : String? = null,
    @SerializedName("height" ) var height : Int?    = null

)