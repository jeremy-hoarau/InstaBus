package com.instabus.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Station (
    val id : Int,
    val street_name : String,
    val city : String,
    val utm_x : String,
    val utm_y : String,
    val lat : Double,
    val lon : Double,
    val furniture : String,
    val buses : String,
    val distance : Double
)