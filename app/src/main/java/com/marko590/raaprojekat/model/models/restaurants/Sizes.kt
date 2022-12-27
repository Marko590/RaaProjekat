package com.marko590.raaprojekat.model.models.restaurants

import com.google.gson.annotations.SerializedName


data class Sizes (

    @SerializedName("medium"    ) var medium    : Medium?    = Medium(),
    @SerializedName("original"  ) var original  : Original?  = Original(),
    @SerializedName("thumbnail" ) var thumbnail : Thumbnail? = Thumbnail()

)