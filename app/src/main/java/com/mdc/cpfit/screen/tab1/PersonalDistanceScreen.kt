package com.mdc.cpfit.screen.tab1

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.model.PersonalDistanceAdapterModel
import com.mdc.cpfit.screen.tab1.adapter.PersonalDistanceAdapter
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.partial_personal_distance.*
import java.io.Serializable


class PersonalDistanceScreen : ScreenUnit() {

    val TAG = PersonalDistanceScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
//    var showOrHiddenCallBack: (() -> Unit)? = null
    var showOrHiddenCallBack: (() -> Unit)? = null


    lateinit var dialog: DialogBase
    val KEY_CALLBACK = "key_callback"

    companion object {
        fun newInstance(): PersonalDistanceScreen {
            val fragment = PersonalDistanceScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        //set bold
        tvStepUnit.setTypeface(null, Typeface.BOLD)
        tvTitleRecord.setTypeface(null, Typeface.BOLD)
        tvTitleStep.setTypeface(null, Typeface.BOLD)
        tvTitleDistance.setTypeface(null, Typeface.BOLD)

        var adapter = PersonalDistanceAdapter(activityMain)
        recRecord.setLayoutManager(LinearLayoutManager(context))
        recRecord.adapter = adapter

        imvAdd.setOnClickListener {
            showOrHiddenCallBack?.invoke()
        }

        //Mockup
        var mockData = ArrayList<PersonalDistanceAdapterModel>()
        mockData.add(
                PersonalDistanceAdapterModel(getString(R.string.personal_title_my_record),123458,4.2F, null, null)
        )
        mockData.add(
                PersonalDistanceAdapterModel(getString(R.string.personal_title_company_record),552493,136.9F, 100000, 999F)
        )
        mockData.add(
                PersonalDistanceAdapterModel(getString(R.string.personal_title_total_record),28552493,14136.9F, 120000000,  999999F )
        )
        adapter.updateArray(mockData)

//        recRecord?.viewTreeObserver?.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                setAnimation()
//                //At this point the layout is complete and the
//                //dimensions of recyclerView and any child views are known.
//                //Remove listener after changed RecyclerView's height to prevent infinite loop
//                recRecord?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
//            }
//
//        })
    }



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




    fun setAnimation(reverse: Boolean = false) {
        recRecord?.post {
            var cardViewItem1 = recRecord?.findViewHolderForAdapterPosition(0)?.itemView
            var cardViewItem2 = recRecord?.findViewHolderForAdapterPosition(1)?.itemView
            var cardViewItem3 = recRecord?.findViewHolderForAdapterPosition(2)?.itemView

            var animItem1 = ObjectAnimator.ofFloat(cardViewItem1, "translationX", 700F, 0F)
            var animItem2 = ObjectAnimator.ofFloat(cardViewItem2, "translationX", 700F, 0F)
            var animItem3 = ObjectAnimator.ofFloat(cardViewItem3, "translationX", 700F, 0F)

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



}