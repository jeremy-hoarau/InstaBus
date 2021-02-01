package com.instabus.ui.station

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.instabus.ImageActivity
import com.instabus.R
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
        holder.imageId = image.uid
    }

    override fun getItemCount(): Int = images.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageTitle: TextView = view.findViewById(R.id.imageTitleFromList)
        val imagePreview: ImageView = view.findViewById(R.id.imagePreviewFromList)
        var imageId: Int = 0

        init {
            view.setOnClickListener{ onClick(view.context, imageId) }
        }

        override fun toString(): String {
            return super.toString() + " '" + imageTitle.text + "'"
        }
    }

    fun setStationImages(stationImages: List<StationImage>)
    {
        images = stationImages
        notifyDataSetChanged()
    }

    fun onClick(context: Context, id: Int)
    {
        val intent = Intent(context, ImageActivity::class.java)
        intent.putExtra("stationId", id)

        context.startActivity(intent)
    }
}