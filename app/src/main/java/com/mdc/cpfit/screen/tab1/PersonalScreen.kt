package com.mdc.cpfit.screen.tab1

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.model.WalkingHistoryReportBody
import com.mdc.cpfit.screen.PinOTPScreen
import com.mdc.cpfit.screen.tab1.adapter.WalkingHistoryReportAdapter
import com.mdc.cpfit.util.ImageUtil
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_personal.*


class PersonalScreen : ScreenUnit() {

    val TAG = PersonalScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    lateinit var header: TextView
    lateinit var dialog: DialogBase
    var flagDistanceShow = true

    companion object {
        fun newInstance(): PersonalScreen {
            val fragment = PersonalScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalScreen::class.simpleName.toString(), rootView)
        setValue()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_personal, container, false)
        return rootView
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

        Glide.with(this.context!!).load(R.drawable.ic_personal_profile)
                .apply(ImageUtil.getImageCirclePersonnalProfile())
                .into(imvProfile)

        viewDistanceContent.setOnClickListener { flipAnimation() }
        viewDistanceContent2.setOnClickListener { flipAnimation() }


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

}