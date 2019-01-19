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
import kotlinx.android.synthetic.main.sc_personal_old2.*
import java.util.*


class PersonalScreenOld2 : ScreenUnit() {

    val TAG = PersonalScreenOld2::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    lateinit var header: TextView
    lateinit var dialog: DialogBase
    var flagDistanceShow = true
    var datePicker = ""

    companion object {
        fun newInstance(): PersonalScreenOld2 {
            val fragment = PersonalScreenOld2()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_personal_old2, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalScreenOld2::class.simpleName.toString(), rootView)
        setValue()

    }


    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        //set profile
        tvStepUnit.setTypeface(null, Typeface.BOLD)
        tvKmUnit.setTypeface(null, Typeface.BOLD)
        tvStepUnit2.setTypeface(null, Typeface.BOLD)


        viewDistanceContent.setOnClickListener { flipAnimation() }
        viewDistanceContent2.setOnClickListener { flipAnimation() }
        tvSelectDate.setOnClickListener { onClickDatePicker() }

    }


    private fun flipAnimation() {
        //Animation Normally
        if(viewDistanceContent.visibility == View.VISIBLE) {
            var animShow = AnimationUtils.loadAnimation(context, R.anim.from_right)
            viewDistanceContent2.startAnimation(animShow)
            animShow.setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {}
                override fun onAnimationStart(animation: Animation?) {
                    viewDistanceContent2.alpha = 1F
                }
            })
            viewDistanceContent.visibility = View.INVISIBLE
        } else {
            var animShow = AnimationUtils.loadAnimation(context, R.anim.from_left)
            viewDistanceContent.startAnimation(animShow)
            animShow.setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {}
                override fun onAnimationStart(animation: Animation?) {
                    viewDistanceContent2.alpha = 0F
                    viewDistanceContent.visibility = View.VISIBLE
                }
            })
        }

        //AnimatorSet
//        var animatorSet = AnimatorSet()
//        animatorSet.setDuration(1000)
//        animatorSet.setInterpolator(AccelerateDecelerateInterpolator())
//        animatorSet.addListener (object: Animator.AnimatorListener {
//            override fun onAnimationRepeat(animation: Animator?) {}
//            override fun onAnimationCancel(animation: Animator?) {}
//            override fun onAnimationStart(animation: Animator?) {
//                flagDistanceShow = !flagDistanceShow
//            }
//            override fun onAnimationEnd(animation: Animator?) {}
//        })
//        if(flagDistanceShow) {
//            var animSetIn = AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_in) as AnimatorSet
//            var animSetOut = AnimatorInflater.loadAnimator(context, R.animator.card_flip_left_out) as AnimatorSet
//            animSetIn.setTarget(viewDistanceContent2)
//            animSetOut.setTarget(viewDistanceContent)
//            animatorSet.playTogether(animSetIn, animSetOut)
//        } else {
//            var animSetIn = AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_in) as AnimatorSet
//            var animSetOut = AnimatorInflater.loadAnimator(context, R.animator.card_flip_right_out) as AnimatorSet
//            animSetIn.setTarget(viewDistanceContent)
//            animSetOut.setTarget(viewDistanceContent2)
//            animatorSet.playTogether(animSetIn, animSetOut)
//        }
//        animatorSet.start()
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
            tvSelectDate.setText(datePicker)

        }, yearPick, monthPick, dayPick)

        dpd.show()
    }

}