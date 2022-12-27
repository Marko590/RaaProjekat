package com.marko590.raaprojekat.model.models.geocoding

import com.google.gson.annotations.SerializedName


data class Address (

    @SerializedName("house_number"   ) var houseNumber    : String? = null,
    @SerializedName("road"           ) var road           : String? = null,
    @SerializedName("quarter"        ) var quarter        : String? = null,
    @SerializedName("suburb"         ) var suburb         : String? = null,
    @SerializedName("city"           ) var city           : String? = null,
    @SerializedName("county"         ) var county         : String? = null,
    @SerializedName("ISO3166-2-lvl6" ) var iso : String? = null,
@SerializedName("state"          ) var state          : String? = null,
@SerializedName("postcode"       ) var postcode       : String? = null,
@SerializedName("country"        ) var country        : String? = null,
@SerializedName("country_code"   ) var countryCode    : String? = null

)