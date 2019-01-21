package com.mdc.cpfit.screen.tab1

import android.app.DatePickerDialog
import android.app.FragmentTransaction
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
    var f1 : PersonalDistanceScreen? = null
    var f2 : PersonalUpdateDistanceScreen? = null


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
        setValue()

    }


    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        //set bold
        tvPoint.setTypeface(null, Typeface.BOLD)

        f1 = PersonalDistanceScreen.newInstance()
        f1?.showOrHiddenCallBack = { showOrHiddenScreen() }
        f2 = PersonalUpdateDistanceScreen.newInstance()
        f2?.showOrHiddenCallBack = { showOrHiddenScreen() }
        ReplaceScreen(f1, R.id.container)

    }

    private fun showOrHiddenScreen() {
        val manager = childFragmentManager
        val currentFragment = childFragmentManager?.findFragmentById(R.id.container)
        if (currentFragment != null) {
            if (currentFragment is PersonalDistanceScreen) {
                val transaction = manager.beginTransaction()
                transaction.setCustomAnimations(
                        R.anim.translate_from_right, R.anim.translate_to_left,
                        R.anim.translate_from_left, R.anim.translate_to_right
                )
                transaction.replace(R.id.container, f2, "Update")
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
                transaction.addToBackStack("ShowDistance")
                transaction.commit()
            } else {
                val transaction = manager.beginTransaction()
                transaction.setCustomAnimations(
                        R.anim.translate_from_right, R.anim.translate_to_left,
                        R.anim.translate_from_left, R.anim.translate_to_right
                )
                transaction.replace(R.id.container, f1, "ShowDistance")
                transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_NONE)
                while (childFragmentManager.getBackStackEntryCount() > 0) {
                    childFragmentManager.popBackStack()
                    break
                }
                transaction.commit()
            }
        }
    }


}