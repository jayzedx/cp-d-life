package com.mdc.cpfit.screen.tab2

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.util.ScreenUnit
import android.support.v7.widget.LinearLayoutManager
import android.util.TypedValue
import android.widget.EditText
import android.widget.TextView
import com.mdc.cpfit.model.LeaderBoardBody
import com.mdc.cpfit.model.LeaderBoardTrophyBody
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.screen.tab2.adapter.LeaderBoardAdapter
import kotlinx.android.synthetic.main.pager_leaderboard.*


class LeaderBoardPagerScreen : ScreenUnit() {

    val TAG = LeaderBoardPagerScreen::class.java.simpleName
    var rootView: View? = null
    var page: String = ""

    companion object {
        fun newInstance(page: String): LeaderBoardPagerScreen {
            val fragment = LeaderBoardPagerScreen()
            val args = Bundle()
            args.putString(MsgProperties.ARGUMENT, page)
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
        page = args?.getString(MsgProperties.ARGUMENT, "")!!

        setComponent()
    }

    private fun setComponent() {

        var adapter = LeaderBoardAdapter(activityMain, page)
        recLeaderboard.setLayoutManager(LinearLayoutManager(context))
        recLeaderboard.adapter = adapter

        //Mockup
        var mockData = ArrayList<LeaderBoardBody>()
        mockData.add(LeaderBoardBody(1,1, "Chalermpong", "Freewill Solutions Co,Ltd.",5.5F, arrayListOf(
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg")
        )))
        mockData.add(LeaderBoardBody(2,2, "Apisit","True Corporation",5.4F, arrayListOf(
                LeaderBoardTrophyBody(1, "Test.jpg"))))

        mockData.add(LeaderBoardBody(3,3, "Sutthiphongsssss", "Siam Makro Public Company Limited",5.3F, arrayListOf(
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg")
        )))

        mockData.add(LeaderBoardBody(4,4, "Sutee","Siam Makro Public Company Limited",5.3F, arrayListOf(
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg")
        )))

        mockData.add(LeaderBoardBody(5,5, "Sutee","Freewill Solutions Co,Ltd.",5.3F, arrayListOf(
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg"),
                LeaderBoardTrophyBody(1, "Test.jpg")
        )))


        adapter.updateArray(mockData)

        var model = mockData

        tvHeadDistance.text = "9.9 " + getString(R.string.leaderboard_km_unit)



        when(page) {
            MsgProperties.LEADERBOARD_COMPANY -> {
                tvHeadName.text = getString(R.string.leaderboard_head_company)
                tvTitleRank1.visibility = View.GONE
                tvTitleRank2.visibility = View.GONE
                tvTitleRank3.visibility = View.GONE
                changeFontSize(tvNameRank1,tvDistanceRank1)
                changeFontSize(tvNameRank2,tvDistanceRank2)
                changeFontSize(tvNameRank3,tvDistanceRank3)
            }
            MsgProperties.LEADERBOARD_ALL_COMPANY -> {
                tvHeadName.text = getString(R.string.leaderboard_head_all_company)
                tvTitleRank1.visibility = View.GONE
                tvTitleRank2.visibility = View.GONE
                tvTitleRank3.visibility = View.GONE
                changeFontSize(tvNameRank1,tvDistanceRank1)
                changeFontSize(tvNameRank2,tvDistanceRank2)
                changeFontSize(tvNameRank3,tvDistanceRank3)
            }
            MsgProperties.LEADERBOARD_ALL_MEMBER -> {
                tvHeadName.text = getString(R.string.leaderboard_head_all_member)
            }
        }

        //bold
        tvNameRank1.setTypeface(null, Typeface.BOLD)
        tvNameRank2.setTypeface(null, Typeface.BOLD)
        tvNameRank3.setTypeface(null, Typeface.BOLD)


        if (model.size>=1) {
            tvNameRank1.text = model.get(0).name
            tvTitleRank1.text = model.get(0).title
            tvDistanceRank1.text = model.get(0).distanceKm.toString() + " " + getString(R.string.leaderboard_km_unit)
        }

        if (model.size>=2) {
            tvNameRank2.text = model.get(1).name
            tvTitleRank2.text = model.get(1).title
            tvDistanceRank2.text = model.get(1).distanceKm.toString() + " " + getString(R.string.leaderboard_km_unit)
        }

        if (model.size>=3) {
            tvNameRank3.text = model.get(2).name
            tvTitleRank3.text = model.get(2).title
            tvDistanceRank3.text = model.get(2).distanceKm.toString() + " " + getString(R.string.leaderboard_km_unit)
        }

    }


    private fun changeFontSize(tv1: TextView, tv2: TextView) {
        val smallPx = context?.resources?.getDimension(R.dimen._11sdp)!!
        val largePx = context?.resources?.getDimension(R.dimen._12sdp)!!
        val smallSize = smallPx / getResources().displayMetrics.density
        val largeSize = largePx / getResources().displayMetrics.density
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, largeSize)
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, smallSize)
    }
}
