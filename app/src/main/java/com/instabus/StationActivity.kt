package com.instabus

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StationActivity : AppCompatActivity() {

    private var stationId: Int = 0
    private lateinit var stationName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station)
        setSupportActionBar(findViewById(R.id.toolbar))

        stationId = intent.getIntExtra("stationId", 0)
        stationName = intent.getStringExtra("stationName")?: ""

        findViewById<Toolbar>(R.id.toolbar).title = stationName

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
//TODO create DB for images: image_table = id, stationId, title, image