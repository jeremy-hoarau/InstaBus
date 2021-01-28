package com.instabus

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.instabus.data.database.AppDatabase
import com.instabus.data.database.StationImageDAO
import com.instabus.dummy.DummyContent

class StationImageListFragment : Fragment() {

    private var columnCount = 1
    private val stationImageListAdapter = MyStationImageRecyclerViewAdapter()
    private lateinit var stationImagesDao: StationImageDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_station_image_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = stationImageListAdapter
            }
        }

        val db = Room.databaseBuilder(
            view.context,
            AppDatabase::class.java, "insta-bus"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        stationImagesDao = db.stationImageDAO()

        return view
    }

    override fun onResume() {
        super.onResume()
        loadStationImages()
    }

    private fun loadStationImages() {
        val activity: StationActivity = getActivity() as StationActivity
        stationImageListAdapter.setStationImages(stationImagesDao.getAllByStationId(activity.stationId))
    }
}