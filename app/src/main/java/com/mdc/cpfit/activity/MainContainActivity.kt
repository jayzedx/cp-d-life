package com.mdc.cpfit.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import com.mdc.cpfit.R
import com.mdc.cpfit.adapter.MainViewPagerAdapter
import com.mdc.cpfit.util.ActivityUnit
import com.mdc.cpfit.util.Contextor
import com.mdc.cpfit.util.Logging
import java.util.*

class MainContainActivity : ActivityUnit() {
    lateinit var pager: ViewPager
    lateinit var tabView: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        containView = R.id.contian_view
        setValue()


    }

    private fun setValue() {
        tabView = findViewById(R.id.tabView)
        pager = findViewById(R.id.pager)
        setupViewPager(pager)
        setTabLayout(tabView, pager)
        Logging.StartLogging()

    }
    private fun setLocale() {
        val languageToLoad = "TH"
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        Contextor.instance.ContextApp?.getResources()?.updateConfiguration(config, Contextor.instance.ContextApp?.getResources()!!.getDisplayMetrics())

    }
    private fun setTabLayout(tabView: TabLayout, pager: ViewPager) {
        tabView.setupWithViewPager(pager)
        setIconTablayout(tabView)
    }


    private fun setIconTablayout(iconTablayout: TabLayout) {

        val tabCount = iconTablayout.tabCount //Assuming you have already somewhere set the adapter for the ViewPager

        for (i in 0 until tabCount) {
            val tab = iconTablayout.getTabAt(i)
            if (tab != null) {
                val myCustomIcon = LayoutInflater.from(iconTablayout.context).inflate(R.id.tabView, null) as ImageView
                when (i) {
                    0 ->
                        myCustomIcon.setImageResource(R.drawable.ic_refresh)
                    1 ->
                        myCustomIcon.setImageResource(R.drawable.ic_refresh)
                    2 ->
                        myCustomIcon.setImageResource(R.drawable.ic_refresh)

                    3 ->
                        myCustomIcon.setImageResource(R.drawable.ic_refresh)

                    4 -> myCustomIcon.setImageResource(R.drawable.ic_refresh)

                }

                /*Here is where to set image if doing it dynamically
                myCustomIcon.setImageBitmap(bitmap);
                */

                tab.customView = myCustomIcon
            }
        }
    }
    private fun setupViewPager(pager: ViewPager?) {
        runOnUiThread {
            val adapter = MainViewPagerAdapter(supportFragmentManager)
//            val f1 = MapAllPersonnelScreen.newInstance("One")
//
//            adapter.addFragment(f1, "TAB 1")
//
//            val f2 = ScheduleServiceListScreen.newInstance("Two")
//            adapter.addFragment(f2, "TAB 2")
//
//            val f3 = PatienListInfoScreen.newInstance("Three")
//            adapter.addFragment(f3, "TAB 3")
//
//            val f4 = ChatListScreen.newInstance("Four")
//            adapter.addFragment(f4, "TAB 4")
//
//
//            val f5 = NavigationScreen.newInstance("five")
//            adapter.addFragment(f5, "TAB 5")

            pager?.adapter = adapter

        }
    }


}
