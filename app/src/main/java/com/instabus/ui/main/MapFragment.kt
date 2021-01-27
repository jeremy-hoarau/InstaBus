package com.instabus.ui.main

import android.content.Intent
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.instabus.R
import com.instabus.StationActivity
import com.instabus.data.models.Station

class MapFragment : Fragment() {

    private lateinit var map:GoogleMap
    private lateinit var stationsList: List<Station>

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(41.3851, 2.1734), 11f))    //Barcelona
        map.setOnMarkerClickListener { marker -> onMarkerClick(marker.position.latitude, marker.position.longitude)}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun setStationsData(stations: List<Station>)
    {
        stationsList = stations
        for (station:Station in stations)
        {
            map.addMarker(MarkerOptions().position(LatLng(station.lat, station.lon)).icon(BitmapDescriptorFactory.fromResource(R.drawable.station_icon)))
        }
    }

    private fun onMarkerClick(lat:Double, lon:Double): Boolean
    {
        val intent = Intent(context, StationActivity::class.java)

        val selectedStation: Station = stationsList.first { station -> station.lat == lat && station.lon == lon }
        intent.putExtra("stationId", selectedStation.id)
        intent.putExtra("stationName", selectedStation.street_name)

        startActivity(intent)
        return true
    }
}