package com.instabus

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.instabus.data.MainViewModel
import com.instabus.data.database.AppDatabase

class StationActivity : AppCompatActivity() {

    var stationId: Int = 0
    private lateinit var stationName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station)

        stationId = intent.getIntExtra("stationId", 0)
        stationName = intent.getStringExtra("stationName")?: ""

        findViewById<Toolbar>(R.id.toolbar).title = stationName

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent = Intent(view.context, TakePictureActivity::class.java)
            intent.putExtra("stationId", stationId)

            startActivity(intent)
        }
    }
}