package com.instabus.ui.main

import android.app.Application
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.instabus.R
import com.instabus.data.Station

import com.instabus.dummy.DummyContent.DummyItem
import com.instabus.utilities.FileHelper


class MyStationsListRecyclerViewAdapter(private val stations: List<Station>): RecyclerView.Adapter<MyStationsListRecyclerViewAdapter.ViewHolder>() {

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

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}