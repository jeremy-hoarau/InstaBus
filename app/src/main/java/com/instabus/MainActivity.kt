package com.instabus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.instabus.data.MainViewModel
import com.instabus.ui.main.SectionsPagerAdapter

class MainActivity() : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getStationsData()
        viewModel.stations.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { sectionsPagerAdapter.setStationsData(it.data.nearstations) }
            }
            else{
                Toast.makeText(this, "Error: cannot get data from the server", Toast.LENGTH_LONG).show()
            }
        })

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}