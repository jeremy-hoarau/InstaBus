package com.instabus.data

import com.instabus.data.models.JsonStationsRequest
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class StationRepository() {

    suspend fun getStationsData(): Response<JsonStationsRequest>
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://barcelonaapi.marcpous.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val service: StationsService = retrofit.create(StationsService::class.java)
        val serviceData = service.getJsonStationData()
        return serviceData
    }
}