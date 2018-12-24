package com.mdc.cpfit.adapter

import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.mdc.cpfit.util.ScreenUnit

class MainViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    val fragments = ArrayList<ScreenUnit>()
    val titles = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)

    }

    override fun getCount(): Int {
        return fragments.size
    }

    fun addFragment(fragment: ScreenUnit, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
}