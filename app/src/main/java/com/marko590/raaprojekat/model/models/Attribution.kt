package com.marko590.raaprojekat.model.models

import com.google.gson.annotations.SerializedName


data class Attribution (

    @SerializedName("format"           ) var format          : String? = null,
    @SerializedName("license_link"     ) var licenseLink     : String? = null,
    @SerializedName("license_text"     ) var licenseText     : String? = null,
    @SerializedName("attribution_link" ) var attributionLink : String? = null,
    @SerializedName("attribution_text" ) var attributionText : String? = null

)