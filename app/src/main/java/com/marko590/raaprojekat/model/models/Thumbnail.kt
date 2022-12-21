package com.marko590.raaprojekat.model.models

import com.google.gson.annotations.SerializedName


data class Thumbnail (

    @SerializedName("url"    ) var url    : String? = null,
    @SerializedName("bytes"  ) var bytes  : Int?    = null,
    @SerializedName("width"  ) var width  : Int?    = null,
    @SerializedName("format" ) var format : String? = null,
    @SerializedName("height" ) var height : Int?    = null

)