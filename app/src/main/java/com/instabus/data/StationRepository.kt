package com.instabus.data

import android.content.Context
import com.instabus.R
import com.instabus.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class StationRepository {
    fun getStationData(context: Context): List<Station>{
        val text = FileHelper.getTextFromResources(context, R.raw.stations)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(JsonStationsRequest::class.java)
        return adapter.fromJson(text)?.data?.nearstations?: emptyList()
    }
}