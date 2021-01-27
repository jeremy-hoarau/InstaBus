package com.instabus.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instabus.R
import com.instabus.StationActivity
import com.instabus.data.models.Station


class MyStationsListRecyclerViewAdapter(): RecyclerView.Adapter<MyStationsListRecyclerViewAdapter.ViewHolder>() {

    private var stations = emptyList<Station>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_station_list_content, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.idView.text = station.id.toString()
        holder.contentView.text = station.street_name
    }

    override fun getItemCount(): Int = stations.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        init {
            view.setOnClickListener{ onClick(view.context, idView.text.toString().toInt(), contentView.text.toString()) }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setStationsData(stationsData: List<Station>)
    {
        stations = stationsData
        notifyDataSetChanged()
    }

    fun onClick(context: Context, id: Int, stationName: String)
    {
        val intent = Intent(context, StationActivity::class.java)
        intent.putExtra("stationId", id)
        intent.putExtra("stationName", stationName)

        context.startActivity(intent)
    }
}