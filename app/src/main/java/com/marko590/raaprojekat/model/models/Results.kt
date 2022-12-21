package com.marko590.raaprojekat.model.models

import android.content.pm.Attribution
import android.provider.MediaStore
import com.google.gson.annotations.SerializedName


data class Results (

    @SerializedName("id"                    ) var id                  : String?                = null,
    @SerializedName("name"                  ) var name                : String?                = null,
    @SerializedName("coordinates"           ) var coordinates         : Coordinates?           = Coordinates(),
    @SerializedName("score"                 ) var score               : Double?                = null,
    @SerializedName("images"                ) var images              : ArrayList<Images>      = arrayListOf(),
    @SerializedName("booking_info"          ) var bookingInfo         : String?                = null,
    @SerializedName("attribution"           ) var attribution         : ArrayList<AttributionUrl> = arrayListOf(),
    @SerializedName("price_tier"            ) var priceTier           : String?                = null,
    @SerializedName("snippet_language_info" ) var snippetLanguageInfo : String?                = null,
    @SerializedName("snippet"               ) var snippet             : String?                = null,
    @SerializedName("location_id"           ) var locationId          : String?                = null,
    @SerializedName("cuisine_score"         ) var cuisineScore        : Double?                = null

)