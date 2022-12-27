package com.marko590.raaprojekat.model.models.geocoding

import com.google.gson.annotations.SerializedName


data class GeocodingResponse (

    @SerializedName("place_id"     ) var placeId     : Long?              = null,
    @SerializedName("licence"      ) var licence     : String?           = null,
    @SerializedName("osm_type"     ) var osmType     : String?           = null,
    @SerializedName("osm_id"       ) var osmId       : Long?              = null,
    @SerializedName("lat"          ) var lat         : String?           = null,
    @SerializedName("lon"          ) var lon         : String?           = null,
    @SerializedName("display_name" ) var displayName : String?           = null,
    @SerializedName("address"      ) var address     : Address?          = Address(),
    @SerializedName("boundingbox"  ) var boundingbox : ArrayList<String> = arrayListOf()

)