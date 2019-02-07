package com.mdc.cpfit.screen

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mdc.cpfit.R
import com.mdc.cpfit.adapter.MainViewPagerAdapter
import com.mdc.cpfit.dialog.MainMenuBottomSheetDialog
import com.mdc.cpfit.screen.tab1.PersonalScreen
import com.mdc.cpfit.screen.tab1.PersonalScreenOld2
import com.mdc.cpfit.screen.tab2.LeaderBoardScreen
import com.mdc.cpfit.util.ScreenUnit
import com.mdc.cpfit.util.view.CustomViewPager
import kotlinx.android.synthetic.main.main_menu.view.*
import kotlinx.android.synthetic.main.toolbar.*


class MainPagerScreen : ScreenUnit() {

    val TAG = MainPagerScreen::class.java.simpleName
    var rootView: View? = null

    lateinit var pager: CustomViewPager
    lateinit var tabView: TabLayout



    companion object {
        fun newInstance(): MainPagerScreen {
            val fragment = MainPagerScreen()
            val args = Bundle()
//            args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.activity_main_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(MainPagerScreen::class.simpleName.toString(), rootView)
        setValue()
    }

    private fun setValue() {
        rootView?.let {view ->
            tabView = view?.findViewById(R.id.tabView)
            pager = view?.findViewById(R.id.pager)

            setupViewPager(pager)
            setTabLayout(tabView, pager)
            setToolbar()
        }


    }

    private fun setToolbar() {
        toolbarMenu?.setOnClickListener {
            showBottomSheet()
        }
    }


    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.main_menu, null)
        var bottomSheet = MainMenuBottomSheetDialog()

        bottomSheet?.setContentView(bottomSheetView)
        bottomSheet?.show(childFragmentManager, TAG)

        bottomSheetView?.profileMenu?.setOnClickListener {
            IntentFragment(ProfileScreen.newInstance())
            bottomSheet?.dismiss()
        }
        bottomSheetView?.historyMenu?.setOnClickListener {
            IntentFragment(WalkingHistoryReportScreen.newInstance())
            bottomSheet?.dismiss()
        }
        bottomSheetView?.logoutMenu?.setOnClickListener {

        }
        bottomSheetView?.cancelMenu?.setOnClickListener {
            bottomSheet?.dismiss()
        }

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
                val myCustomIcon = LayoutInflater.from(iconTablayout.context).inflate(R.layout.view_tab, null) as ImageView
                when (i) {
                    0 ->
                        myCustomIcon.setImageResource(R.drawable.ic_white_user)
                    1 ->
                        myCustomIcon.setImageResource(R.drawable.ic_white_trophy)
                }

                /*
                Here is where to set image if doing it dynamically
                myCustomIcon.setImageBitmap(bitmap);
                */

                tab.customView = myCustomIcon
            }
        }
    }

    private fun setupViewPager(pager: CustomViewPager?) {
        val f1 = PersonalScreen.newInstance()
        val f2 = PersonalScreenOld2.newInstance()
        val f3 = LeaderBoardScreen.newInstance()
        var adapter = MainViewPagerAdapter(childFragmentManager)
        adapter.addFragment(f1, "TAB 1")
        adapter.addFragment(f2, "TAB 2")
        adapter.addFragment(f3, "TAB 3")
        pager?.setPagingEnabled(false)
        pager?.adapter = adapter
        pager?.offscreenPageLimit = 3
        pager?.setPageTransformer(false, object: ViewPager.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                //Consider animating view here
                if (position >= -1 && position <= 1) {
//                if (pager.currentItem == 0 && position == 0F) {
                    f1?.f1?.setAnimation()
                }
            }

        })
    }


    fun handleViewPagerBackStack(): Boolean {
        val fragment: Fragment? = getCurrentFragment(childFragmentManager, pager)
        return fragment?.let {
            return handleNestedFragmentBackStack(fragment.childFragmentManager)
        } ?: run {
            false
        }
    }


    private fun getCurrentFragment(fragmentManager: FragmentManager, viewPager: ViewPager): Fragment? {
        val fragmentList = fragmentManager.fragments
        val currentSize = fragmentList.size
        val maxSize = viewPager.adapter?.count ?: 0
        val lastPosition = if (maxSize > 0) maxSize - 1 else 0
        val position = viewPager.currentItem
        val offset = viewPager.offscreenPageLimit
        return when {
            maxSize == 0 -> null
            position <= offset -> fragmentList[position]
            position == lastPosition -> fragmentList[currentSize - 1]
            position > lastPosition - offset -> fragmentList[maxSize - offset]
            currentSize < maxSize -> fragmentList[offset]
            currentSize >= maxSize -> fragmentList[position]
            else -> null
        }
    }

    private fun handleNestedFragmentBackStack(fragmentManager: FragmentManager): Boolean {
        val childFragmentList = fragmentManager.fragments
        if (childFragmentList.size > 0) {
            for (index in childFragmentList.size - 1 downTo 0) {
                val fragment = childFragmentList[index]
                val isPopped = handleNestedFragmentBackStack(fragment.childFragmentManager)
                return when {
                    isPopped -> true
                    fragmentManager.backStackEntryCount > 0 -> {
                        fragmentManager.popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }
        return false
    }





}