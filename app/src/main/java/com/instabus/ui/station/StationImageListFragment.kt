package com.instabus.ui.station

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.room.Room
import com.instabus.R
import com.instabus.StationActivity
import com.instabus.data.database.AppDatabase
import com.instabus.data.database.StationImageDAO

class StationImageListFragment : Fragment() {

    private var columnCount = 1
    private val stationImageListAdapter = MyStationImageRecyclerViewAdapter()
    private lateinit var stationImagesDao: StationImageDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        //setup db
        val db = Room.databaseBuilder(
            view.context,
            AppDatabase::class.java, "insta-bus"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        stationImagesDao = db.stationImageDAO()

        //setup slide delete
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {

                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val holder = viewHolder as MyStationImageRecyclerViewAdapter.ViewHolder
                    deleteImage(holder.imageId)
                    loadStationImages()
                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(view as RecyclerView)

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

    private fun deleteImage(imageId: Int)
    {
        stationImagesDao.delete(stationImagesDao.findByImageId(imageId))
    }
}