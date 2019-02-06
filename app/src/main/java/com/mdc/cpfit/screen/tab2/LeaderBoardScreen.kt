package com.mdc.cpfit.screen.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.adapter.MainViewPagerAdapter
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.screen.tab1.PersonalScreen
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_leaderboard.*


class LeaderBoardScreen : ScreenUnit() {

    val TAG = LeaderBoardScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var adapter: MainViewPagerAdapter


    companion object {
        fun newInstance(): LeaderBoardScreen {
            val fragment = LeaderBoardScreen()
            val args = Bundle()
            //args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_leaderboard, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(LeaderBoardScreen::class.simpleName.toString(), rootView)
        setValue()
    }



    private fun setValue() {
        val args = arguments
        //type = args?.getString(MsgProperties.PERSON_TYPE, "")!!
        setComponent()
    }

    private fun setComponent() {
        setViewPager()
    }

    private fun setViewPager() {
        var model = null
        adapter = MainViewPagerAdapter(childFragmentManager)
        adapter.addFragment(LeaderBoardPagerScreen.newInstance(MsgProperties.LEADERBOARD_COMPANY), "TAB1")
        adapter.addFragment(LeaderBoardPagerScreen.newInstance(MsgProperties.LEADERBOARD_ALL_COMPANY), "TAB2")
        adapter.addFragment(LeaderBoardPagerScreen.newInstance(MsgProperties.LEADERBOARD_ALL_MEMBER), "TAB3")
        adapter.addFragment(TrophyScreen.newInstance(), "TAB4")
        pager?.offscreenPageLimit = 3
        pager?.adapter = adapter
        indicator.setViewPager(pager)
    }


}