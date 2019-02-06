package com.mdc.cpfit.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mdc.cpfit.util.ScreenUnit


class ViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
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