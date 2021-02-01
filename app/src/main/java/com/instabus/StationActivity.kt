package com.instabus

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class StationActivity : AppCompatActivity() {

    var stationId: Int = 0
    private lateinit var stationName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station)

        stationId = intent.getIntExtra("stationId", 0)
        stationName = intent.getStringExtra("stationName")?: ""


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            val intent = Intent(view.context, TakePictureActivity::class.java)
            intent.putExtra("stationId", stationId)

            startActivity(intent)
        }

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = stationName

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}