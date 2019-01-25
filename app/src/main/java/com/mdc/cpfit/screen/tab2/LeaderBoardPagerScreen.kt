package com.mdc.cpfit.screen.tab2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.util.ScreenUnit


class LeaderBoardPagerScreen : ScreenUnit() {

    val TAG = LeaderBoardPagerScreen::class.java.simpleName
    var rootView: View? = null

    companion object {
        fun newInstance(): LeaderBoardPagerScreen {
            val fragment = LeaderBoardPagerScreen()
            val args = Bundle()
            //args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(LeaderBoardPagerScreen::class.simpleName.toString(), rootView)
        setValue()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.pager_leaderboard, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
        //type = args?.getString(MsgProperties.PERSON_TYPE, "")!!
    }
}