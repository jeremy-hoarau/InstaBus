package com.instabus.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JsonStationsRequest(
    val code : Int,
    val data : Data
)

@JsonClass(generateAdapter = true)
data class Data (
    val nearstations : List<Station>,
    val transport : String
)
