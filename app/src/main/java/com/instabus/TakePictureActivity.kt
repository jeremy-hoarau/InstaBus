package com.instabus

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.instabus.data.database.AppDatabase
import com.instabus.data.database.StationImageDAO
import com.instabus.data.database.tables.StationImage
import java.io.ByteArrayOutputStream

class TakePictureActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1
    var stationId: Int = 0

    lateinit var imageByteArray: ByteArray
    private lateinit var stationImagesDao: StationImageDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)

        stationId = intent.getIntExtra("stationId", 0)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "insta-bus"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        stationImagesDao = db.stationImageDAO()

        findViewById<Button>(R.id.okButton).setOnClickListener{
            saveImage()
            finish()
        }

        dispatchTakePictureIntent()
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.imagePreview).setImageBitmap(imageBitmap)

            val bos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            imageByteArray = bos.toByteArray()
        }
        else{
            finish()
        }
    }

    private fun saveImage()
    {
        val imageTitle: String = findViewById<EditText>(R.id.imageTitle).text.toString()
        val stationImage = StationImage(0, stationId, imageTitle, imageByteArray)

        stationImagesDao.insertStationImage(stationImage)
    }
}