package com.mdc.cpfit.screen.tab1

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.model.WalkingHistoryReportBody
import com.mdc.cpfit.screen.PinOTPScreen
import com.mdc.cpfit.screen.tab1.adapter.WalkingHistoryReportAdapter
import com.mdc.cpfit.util.ImageUtil
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_personal_old.*
import retrofit2.Response



class PersonalScreenOld : ScreenUnit() {

    val TAG = PersonalScreenOld::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    lateinit var header: TextView
    lateinit var dialog: DialogBase

    companion object {
        fun newInstance(): PersonalScreenOld {
            val fragment = PersonalScreenOld()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalScreenOld::class.simpleName.toString(), rootView)
        setValue()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_personal_old, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        //set profile
        Glide.with(this.context!!).load(R.drawable.ic_personal_profile)
                .apply(ImageUtil.getImageCirclePersonnalProfile())
                .into(imvProfile)

        //set recycler view
        var adapter = WalkingHistoryReportAdapter(activityMain)
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


        viewStepBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                ReplaceFragment(PinOTPScreen.newInstance())
            }

        })


    }

}