package com.instabus

import android.R.attr.bitmap
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instabus.data.database.tables.StationImage


class MyStationImageRecyclerViewAdapter : RecyclerView.Adapter<MyStationImageRecyclerViewAdapter.ViewHolder>() {

    private var images: List<StationImage> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_station_image_list_content, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]

        val bitmapImage: Bitmap = BitmapFactory.decodeByteArray(image.image, 0 , image.image.size);

        holder.imagePreview.setImageBitmap(bitmapImage)
        holder.imageTitle.text = image.title
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageTitle: TextView = view.findViewById(R.id.imageTitleFromList)
        val imagePreview: ImageView = view.findViewById(R.id.imagePreviewFromList)

        override fun toString(): String {
            return super.toString() + " '" + imageTitle.text + "'"
        }
    }

    fun setStationImages(stationImages: List<StationImage>)
    {
        images = stationImages
        notifyDataSetChanged()
    }
    //TODO image preview on click
    //TODO be able to delete image on slide
}