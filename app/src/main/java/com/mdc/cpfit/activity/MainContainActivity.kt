package com.mdc.cpfit.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView
import com.mdc.cpfit.R
import com.mdc.cpfit.adapter.MainViewPagerAdapter
import com.mdc.cpfit.screen.MainPagerScreen
import com.mdc.cpfit.screen.tab1.PersonalScreen
import com.mdc.cpfit.screen.tab1.PersonalScreenOld2
import com.mdc.cpfit.util.ActivityUnit
import com.mdc.cpfit.util.Contextor
import com.mdc.cpfit.util.Logging
import java.util.*

class MainContainActivity : ActivityUnit() {
//    lateinit var pager: ViewPager
//    lateinit var tabView: TabLayout

    var mainPager = MainPagerScreen.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        containView = R.id.contian_view
        setValue()
        ReplaceFragment(mainPager)

    }


    private fun setLocale() {
        val languageToLoad = "TH"
        val locale = Locale(languageToLoad)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        Contextor.instance.ContextApp?.getResources()?.updateConfiguration(config, Contextor.instance.ContextApp?.getResources()!!.getDisplayMetrics())

    }

    private fun setValue() {
//        tabView = findViewById(R.id.tabView)
//        pager = findViewById(R.id.pager)
//        setupViewPager(pager)
//        setTabLayout(tabView, pager)
        Logging.StartLogging()

    }

//    private fun setTabLayout(tabView: TabLayout, pager: ViewPager) {
//        tabView.setupWithViewPager(pager)
//        setIconTablayout(tabView)
//    }
//
//
//    private fun setIconTablayout(iconTablayout: TabLayout) {
//
//        val tabCount = iconTablayout.tabCount //Assuming you have already somewhere set the adapter for the ViewPager
//
//        for (i in 0 until tabCount) {
//            val tab = iconTablayout.getTabAt(i)
//            if (tab != null) {
//                val myCustomIcon = LayoutInflater.from(iconTablayout.context).inflate(R.layout.view_tab, null) as ImageView
//                when (i) {
//                    0 ->
//                        myCustomIcon.setImageResource(R.drawable.ic_white_user)
//                    1 ->
//                        myCustomIcon.setImageResource(R.drawable.ic_white_trophy)
//
//                }
//
//                /*
//                Here is where to set image if doing it dynamically
//                myCustomIcon.setImageBitmap(bitmap);
//                */
//
//                tab.customView = myCustomIcon
//            }
//        }
//    }
//    private fun setupViewPager(pager: ViewPager?) {
//        runOnUiThread {
//            val f1 = PersonalScreen.newInstance()
//            val f2 = PersonalScreenOld2.newInstance()
//            var adapter = MainViewPagerAdapter(supportFragmentManager)
//            adapter.addFragment(f1, "TAB 1")
//            adapter.addFragment(f2, "TAB 2")
//            pager?.adapter = adapter
//
//        }
//    }

    override fun onBackPressed() {
        val isFragmentPopped = mainPager.handleViewPagerBackStack()
        if (!isFragmentPopped) {
            super.onBackPressed()
        }
    }



//    override fun onBackPressed() {
//        val isFragmentPopped = handleViewPagerBackStack()
//        if (!isFragmentPopped) {
//            super.onBackPressed()
//        }
//    }


//    private fun handleViewPagerBackStack(): Boolean {
//        val fragment: Fragment? = getCurrentFragment(supportFragmentManager, p)
//        return fragment?.let {
//            handleNestedFragmentBackStack(fragment.childFragmentManager)
//        } ?: run {
//            false
//        }
//    }



//    private fun getCurrentFragment(fragmentManager: FragmentManager, viewPager: ViewPager): Fragment? {
//        val fragmentList = fragmentManager.fragments
//        val currentSize = fragmentList.size
//        val maxSize = viewPager.adapter?.count ?: 0
//        val lastPosition = if (maxSize > 0) maxSize - 1 else 0
//        val position = viewPager.currentItem
//        val offset = viewPager.offscreenPageLimit
//        return when {
//            maxSize == 0 -> null
//            position <= offset -> fragmentList[position]
//            position == lastPosition -> fragmentList[currentSize - 1]
//            position > lastPosition - offset -> fragmentList[maxSize - offset]
//            currentSize < maxSize -> fragmentList[offset]
//            currentSize >= maxSize -> fragmentList[position]
//            else -> null
//        }
//    }



//    private fun handleNestedFragmentBackStack(fragmentManager: FragmentManager): Boolean {
//        val childFragmentList = fragmentManager.fragments
//        if (childFragmentList.size > 0) {
//            for (index in childFragmentList.size - 1 downTo 0) {
//                val fragment = childFragmentList[index]
//                val isPopped = handleNestedFragmentBackStack(fragment.childFragmentManager)
//                return when {
//                    isPopped -> true
//                    fragmentManager.backStackEntryCount > 0 -> {
//                        fragmentManager.popBackStack()
//                        true
//                    }
//                    else -> false
//                }
//            }
//        }
//        return false
//    }



}
