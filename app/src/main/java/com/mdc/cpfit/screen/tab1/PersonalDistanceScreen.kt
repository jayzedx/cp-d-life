package com.mdc.cpfit.screen.tab1

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.app.FragmentTransaction
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.partial_personal_distance.*


class PersonalDistanceScreen : ScreenUnit() {

    val TAG = PersonalDistanceScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    var showOrHiddenCallBack: (() -> Unit)? = null


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
//        setAnimation()
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

//
//    /**
//     * rotation, alpha, scaleX/Y, rotationX/Y, translationX/Y
//      */
//    private fun setAnimation(view: View, animationName: String, timeInterpolar: TimeInterpolator, fromValue: Float = 0.1F, toValue: Float = 0.1F ,duration: Long, delay: Long = 0, animatorListener: Animator.AnimatorListener, reverse : Boolean = false) {
////       var anim = ObjectAnimator()
////        anim.setTarget(view)
////        anim.setProperty(View.ALPHA)
////        anim.setFloatValues(0.1F)
//        var anim = ObjectAnimator.ofFloat(view, animationName, fromValue, toValue)
//        anim.setDuration(duration)
//        anim.setStartDelay(delay)
//        anim.setInterpolator(timeInterpolar)
//        if (reverse) anim.reverse()
//        anim.addListener(animatorListener)
////        anim.start()
//
//        var animSet = AnimatorSet()
//        animSet.duration = 1000
//        animSet.playTogether(anim,anim)
//        anim.start()
//    }

    private fun setAnimation(reverse: Boolean = false) {
        var animItem1 = ObjectAnimator.ofFloat(cardViewItem1, "translationX", 1000F, 0F)
        var animItem2 = ObjectAnimator.ofFloat(cardViewItem2, "translationX", 1000F, 0F)
        var animItem3 = ObjectAnimator.ofFloat(cardViewItem3, "translationX", 1000F, 0F)

        animItem1.startDelay = 0
        animItem2.startDelay = 90
        animItem3.startDelay = 180

        if(reverse) {
            animItem1.repeatMode = ObjectAnimator.REVERSE
            animItem2.repeatMode = ObjectAnimator.REVERSE
            animItem3.repeatMode = ObjectAnimator.REVERSE
        }

        var animSet = AnimatorSet()
        animSet.setDuration(1000)
        animSet.setInterpolator(OvershootInterpolator())
        animSet.playTogether(animItem1, animItem2, animItem3)
        animSet.start()

    }


}