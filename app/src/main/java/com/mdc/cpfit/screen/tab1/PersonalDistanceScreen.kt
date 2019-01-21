package com.mdc.cpfit.screen.tab1

import android.app.FragmentTransaction
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.partial_personal_distance.*


class PersonalDistanceScreen: ScreenUnit() {

    val TAG = PersonalDistanceScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    var showOrHiddenCallBack:(()-> Unit)? = null


    lateinit var dialog: DialogBase
    var datePicker = ""

    companion object {
        fun newInstance(): PersonalDistanceScreen {
            val fragment = PersonalDistanceScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.partial_personal_distance, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalDistanceScreen::class.simpleName.toString(), rootView)
        setValue()

    }


    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        //set bold
        tvStepUnit.setTypeface(null, Typeface.BOLD)
        tvTitleRecord.setTypeface(null, Typeface.BOLD)
        tvTitleStep.setTypeface(null, Typeface.BOLD)
        tvTitleDistance.setTypeface(null, Typeface.BOLD)
        tvTitleMyRecord.setTypeface(null, Typeface.BOLD_ITALIC)
        tvTitleCompanyRecord.setTypeface(null, Typeface.BOLD_ITALIC)
        tvTitleTotalRecord.setTypeface(null, Typeface.BOLD_ITALIC)
        tvMyRecordStep.setTypeface(null, Typeface.BOLD)
        tvMyRecordDistance.setTypeface(null, Typeface.BOLD)
        tvCompanyRecordStep.setTypeface(null, Typeface.BOLD)
        tvCompanyRecordDistance.setTypeface(null, Typeface.BOLD)
//        tvCompanyRecordTotalStep.setTypeface(null, Typeface.BOLD)
//        tvCompanyRecordTotalDistance.setTypeface(null, Typeface.BOLD)
        tvTotalRecordStep.setTypeface(null, Typeface.BOLD)
        tvTotalRecordDistance.setTypeface(null, Typeface.BOLD)
//        tvTotalRecordTotalStep.setTypeface(null, Typeface.BOLD)
//        tvTotalRecordTotalDistance.setTypeface(null, Typeface.BOLD)



        imvAdd.setOnClickListener {
            showOrHiddenCallBack?.invoke()
        }

    }





}