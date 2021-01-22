package com.instabus.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.instabus.R
import com.instabus.data.models.Station

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    private var stationsListFragment = StationsListFragment()
    private var mapFragment = MapFragment()

    override fun getItem(position: Int): Fragment {
        return if(position == 0)
            stationsListFragment
        else
            mapFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }

    fun setStationsData(stationsData: List<Station>)
    {
        stationsListFragment.stationsListAdapter.setStationsData(stationsData)
        mapFragment.setStationsData(stationsData)
    }

}