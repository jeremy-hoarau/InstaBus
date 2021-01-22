package com.instabus.data

import android.database.Observable
import com.instabus.data.models.JsonStationsRequest
import retrofit2.Response
import retrofit2.http.GET

interface StationsService {
    @GET("bus/nearstation/latlon/ 41.3985182/2.1917991/1.json")
    suspend fun getJsonStationData(): Response<JsonStationsRequest>
}