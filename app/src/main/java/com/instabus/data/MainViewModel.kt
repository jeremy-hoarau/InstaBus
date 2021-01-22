package com.instabus.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.instabus.data.models.JsonStationsRequest
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(): ViewModel() {
    var stations = MutableLiveData<Response<JsonStationsRequest>>()
    private var stationRepository = StationRepository()

    fun getStationsData()
    {
        viewModelScope.launch {
            stations.value = stationRepository.getStationsData()
        }
    }
}