package com.mdc.cpfit.screen.tab1

import android.app.DatePickerDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_personal.*
import java.util.*



class PersonalScreen : ScreenUnit() {

    val TAG = PersonalScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    lateinit var header: TextView
    lateinit var dialog: DialogBase
    var flagDistanceShow = true
    var datePicker = ""

    companion object {
        fun newInstance(): PersonalScreen {
            val fragment = PersonalScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_personal, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalScreen::class.simpleName.toString(), rootView)
        //setValue()

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
        tvTitleMyRecord.setTypeface(null, Typeface.BOLD)
        tvTitleCompanyRecord.setTypeface(null, Typeface.BOLD)
        tvTitleTotalRecord.setTypeface(null, Typeface.BOLD)
        tvPoint.setTypeface(null, Typeface.BOLD)



    }


//    private fun onClickDatePicker() {
//        val c = Calendar.getInstance()
//        val currentDate = c.get(Calendar.DAY_OF_MONTH).toString() + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR)
//        val dateArray = currentDate?.split("-")
//        var yearPick = dateArray!![2].toInt()
//        var monthPick = dateArray[1].toInt()
//        var dayPick = dateArray[0].toInt()
//
//        datePicker = currentDate
//
//        var cal = Calendar.getInstance()
//        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            cal.set(Calendar.YEAR, year)
//            cal.set(Calendar.MONTH, monthOfYear)
//            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
//            //val myFormat = "dd MMMM yyyy" // mention the format you need
//            //val sdf = SimpleDateFormat(myFormat, Locale.US)
//
//            yearPick = year
//            monthPick = monthOfYear + 1
//            dayPick = dayOfMonth
//            datePicker = "$dayPick-$monthPick-$yearPick"
//            tvSelectDate.setText(datePicker)
//
//        }, yearPick, monthPick, dayPick)
//
//        dpd.show()
//    }

}