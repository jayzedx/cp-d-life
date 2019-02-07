package com.mdc.cpfit.screen


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.model.WalkingHistoryReportBody
import com.mdc.cpfit.screen.tab1.adapter.WalkingHistoryAdapter
import com.mdc.cpfit.screen.tab1.adapter.WalkingHistoryReportAdapter
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_walking_history_report.*
import kotlinx.android.synthetic.main.toolbar_back.*
import java.util.ArrayList


class WalkingHistoryReportScreen : ScreenUnit() {

    val TAG = WalkingHistoryReportScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""

    companion object {
        fun newInstance(): WalkingHistoryReportScreen {
            val fragment = WalkingHistoryReportScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(WalkingHistoryReportScreen::class.simpleName.toString(), rootView)
        setValue()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_walking_history_report, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
        setToolbar()
        setComponent()
    }

    private fun setToolbar() {
        backMenu?.setOnClickListener {
            goback(false)
        }
    }
    private fun setComponent() {

        //set recycler view
        var adapter = WalkingHistoryReportAdapter(activityMain, this)
        recHistory.setLayoutManager(LinearLayoutManager(context))
        recHistory.adapter = adapter

        //Mockup
        var mockData = ArrayList<WalkingHistoryReportBody>()
        mockData.add(WalkingHistoryReportBody(1,1, 50.5F,30F))
        mockData.add(WalkingHistoryReportBody(2,2, 51.5F,30.1F))
        mockData.add(WalkingHistoryReportBody(3,3, 52.5F,30.2F))
        mockData.add(WalkingHistoryReportBody(4,4, 53.5F,30F))
        mockData.add(WalkingHistoryReportBody(5,5, 54.5F,30F))
        mockData.add(WalkingHistoryReportBody(6,6, 50F,30F))
        mockData.add(WalkingHistoryReportBody(7,7, 51.5F,30.5F))
        mockData.add(WalkingHistoryReportBody(8,8, 52.5F,30F))
        mockData.add(WalkingHistoryReportBody(9,9, 53.5F,30.2F))
        mockData.add(WalkingHistoryReportBody(10,10, 54.5F,30F))
        mockData.add(WalkingHistoryReportBody(11,11, 55.5F,30F))
        adapter.updateArray(mockData)
    }


}
