package com.mdc.cpfit.screen.tab1

import android.app.DatePickerDialog
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import java.util.*
import kotlinx.android.synthetic.main.partial_personal_add_distance.*
import kotlinx.android.synthetic.main.partial_personal_distance.*


class PersonalUpdateDistanceScreen: ScreenUnit() {

    val TAG = PersonalUpdateDistanceScreen::class.java.simpleName
    var rootView: View? = null
    var showOrHiddenCallBack:(()-> Unit)? = null

    var stepUnitSelected = true


    lateinit var dialog: DialogBase
    var datePicker = ""

    companion object {
        fun newInstance(): PersonalUpdateDistanceScreen {
            val fragment = PersonalUpdateDistanceScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.partial_personal_add_distance, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalUpdateDistanceScreen::class.simpleName.toString(), rootView)
        setValue()

    }


    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        tvDistanceSelectUnit.text =  getString(R.string.personal_separator_unit_selected) + getString(R.string.personal_distance_selected)
        tvStepSelectUnit.setTypeface(null, Typeface.BOLD)

        imgBtnCancel.setOnClickListener { showOrHiddenCallBack?.invoke() }
        edtSelectDate.setOnClickListener { onClickDatePicker() }
        tvStepSelectUnit.setOnClickListener { onClickChangeUnit()}
        tvDistanceSelectUnit.setOnClickListener { onClickChangeUnit()}

    }

    private fun onClickChangeUnit() {
        stepUnitSelected = if (stepUnitSelected) {
            tvDistanceSelectUnit.text = getString(R.string.personal_distance_selected)
            tvStepSelectUnit.text = getString(R.string.personal_step_selected) + getString(R.string.personal_separator_unit_selected)
            tvStepSelectUnit.setTypeface(null, Typeface.NORMAL)
            tvDistanceSelectUnit.setTypeface(null, Typeface.BOLD)
            false
        } else {
            tvStepSelectUnit.text = getString(R.string.personal_step_selected)
            tvDistanceSelectUnit.text =  getString(R.string.personal_separator_unit_selected) + getString(R.string.personal_distance_selected)
            tvStepSelectUnit.setTypeface(null, Typeface.BOLD)
            tvDistanceSelectUnit.setTypeface(null, Typeface.NORMAL)
            true
        }

    }


    private fun onClickDatePicker() {
        val c = Calendar.getInstance()
        val currentDate = c.get(Calendar.DAY_OF_MONTH).toString() + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.YEAR)
        val dateArray = currentDate?.split("-")
        var yearPick = dateArray!![2].toInt()
        var monthPick = dateArray[1].toInt()
        var dayPick = dateArray[0].toInt()

        datePicker = currentDate

        var cal = Calendar.getInstance()
        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            //val myFormat = "dd MMMM yyyy" // mention the format you need
            //val sdf = SimpleDateFormat(myFormat, Locale.US)

            yearPick = year
            monthPick = monthOfYear + 1
            dayPick = dayOfMonth
            datePicker = "$dayPick-$monthPick-$yearPick"
            edtSelectDate.setText(datePicker)

        }, yearPick, monthPick, dayPick)
        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }

}