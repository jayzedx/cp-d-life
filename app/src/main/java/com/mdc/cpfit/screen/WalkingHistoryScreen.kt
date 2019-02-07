package com.mdc.cpfit.screen

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.model.WalkingHistoryBody
import com.mdc.cpfit.screen.tab1.adapter.WalkingHistoryAdapter
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_walking_history.*
import kotlinx.android.synthetic.main.toolbar_back.*
import java.util.ArrayList


class WalkingHistoryScreen : ScreenUnit() {

    val TAG = WalkingHistoryScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""

    companion object {
        fun newInstance(): WalkingHistoryScreen {
            val fragment = WalkingHistoryScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(WalkingHistoryScreen::class.simpleName.toString(), rootView)
        setValue()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_walking_history, container, false)
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

        var adapter = WalkingHistoryAdapter(activityMain)
        recHistory.setLayoutManager(LinearLayoutManager(context))
        recHistory.adapter = adapter

        //Mockup
        var mockData = ArrayList<WalkingHistoryBody>()
        mockData.add(WalkingHistoryBody(1,1000, 10F,"2018-01-31"))
        mockData.add(WalkingHistoryBody(2,2000, 11F,"2018-01-30"))
        mockData.add(WalkingHistoryBody(3,500, 12F,"2018-01-29"))
        mockData.add(WalkingHistoryBody(4,700, 13F,"2018-01-28"))
        adapter.updateArray(mockData)
    }


}
