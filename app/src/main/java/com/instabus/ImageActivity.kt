package com.instabus

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.instabus.data.database.AppDatabase
import com.instabus.data.database.StationImageDAO
import com.instabus.data.database.tables.StationImage

class ImageActivity : AppCompatActivity() {

    private lateinit var stationImagesDao: StationImageDAO
    private lateinit var stationImage: StationImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val db = Room.databaseBuilder(
                baseContext,
                AppDatabase::class.java, "insta-bus"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        stationImagesDao = db.stationImageDAO()

        stationImage = stationImagesDao.findByImageId(intent.getIntExtra("stationId", 0))
        val bitmapImage: Bitmap = BitmapFactory.decodeByteArray(stationImage.image, 0 , stationImage.image.size);

        findViewById<TextView>(R.id.imageTitle).text = stationImage.title
        findViewById<ImageView>(R.id.stationImageView).setImageBitmap(bitmapImage)

        supportActionBar?.apply {
            title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}