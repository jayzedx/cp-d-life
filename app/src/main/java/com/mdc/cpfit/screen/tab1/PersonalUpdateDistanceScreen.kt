package com.mdc.cpfit.screen.tab1

import android.app.DatePickerDialog
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.DateUtil
import com.mdc.cpfit.util.ScreenUnit
import java.util.*
import kotlinx.android.synthetic.main.partial_personal_add_distance.*
import kotlinx.android.synthetic.main.partial_personal_distance.*
import java.io.Serializable
import java.text.SimpleDateFormat




class PersonalUpdateDistanceScreen: ScreenUnit() {

    val TAG = PersonalUpdateDistanceScreen::class.java.simpleName
    var rootView: View? = null
    var showOrHiddenCallBack:(()-> Unit)? = null

    var stepUnitSelected = true


    lateinit var dialog: DialogBase
    var datePicker = ""
    val KEY_CALLBACK = "key_callback"


    companion object {
        fun newInstance(): PersonalUpdateDistanceScreen {
            val fragment = PersonalUpdateDistanceScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //if (savedInstanceState != null) {
        //    showOrHiddenCallBack = savedInstanceState.getSerializable(KEY_CALLBACK) as () -> Unit
        //}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putSerializable(KEY_CALLBACK, showOrHiddenCallBack as Serializable)
    }


    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        stepUnitSelected = true
        tvKmSelectUnit.text =  "/" + getString(R.string.personal_km_selected)
        tvStepSelectUnit.setTypeface(null, Typeface.BOLD)

        imgBtnCancel.setOnClickListener { showOrHiddenCallBack?.invoke() }
        edtSelectDate.setOnClickListener { onClickDatePicker() }
        tvStepSelectUnit.setOnClickListener { onClickChangeUnit()}
        tvKmSelectUnit.setOnClickListener { onClickChangeUnit()}

    }

    private fun onClickChangeUnit() {
        val smallPx = context?.resources?.getDimension(R.dimen._12sdp)!!
        val largePx = context?.resources?.getDimension(R.dimen._14sdp)!!
        val smallSize = smallPx / getResources().displayMetrics.density
        val largeSize = largePx / getResources().displayMetrics.density

        stepUnitSelected = if (stepUnitSelected) {
            tvKmSelectUnit.text = getString(R.string.personal_km_selected)
            tvStepSelectUnit.text = getString(R.string.personal_step_selected) + "/"
            tvStepSelectUnit.setTypeface(null, Typeface.NORMAL)
            tvKmSelectUnit.setTypeface(null, Typeface.BOLD)
            tvKmSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, largeSize)
            tvStepSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, smallSize)
            false
        } else {
            tvStepSelectUnit.text = getString(R.string.personal_step_selected)
            tvKmSelectUnit.text =  "/" + getString(R.string.personal_km_selected)
            tvStepSelectUnit.setTypeface(null, Typeface.BOLD)
            tvKmSelectUnit.setTypeface(null, Typeface.NORMAL)
            tvStepSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, largeSize)
            tvKmSelectUnit.setTextSize(TypedValue.COMPLEX_UNIT_SP, smallSize)
            true
        }

    }


    private fun onClickDatePicker() {
        val c = Calendar.getInstance()
        val currentDate = c.get(Calendar.DAY_OF_MONTH).toString() + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR)
        var dateArray : List<String>
        var yearPick = 0
        var monthPick = 0
        var dayPick = 0

        dateArray = if (datePicker.equals("")) {
            currentDate?.split("/")
        } else {
            datePicker.split("/")
        }
        dayPick = dateArray[0].toInt()
        monthPick = dateArray[1].toInt().let {month->
            if (datePicker.equals("")) month
            else month-1
        }
        yearPick = dateArray!![2].toInt()

        var cal = Calendar.getInstance()
        val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

//            val myFormat = "dd MMMM yyyy" // mention the format you need
//            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
//            c.set(year, monthOfYear, dayOfMonth)
//            val dateString = sdf.format(c.time)

            var month = monthOfYear + 1
            datePicker = "$dayOfMonth/$month/$year"
            edtSelectDate.setText(DateUtil.convertDateFormatServer(datePicker))

        }, yearPick, monthPick, dayPick)
        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }

}