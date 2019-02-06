package com.mdc.cpfit.screen.tab1

import android.app.DatePickerDialog
import android.app.FragmentTransaction
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.gdacciaro.iOSDialog.iOSDialogBuilder
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_personal, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalScreen::class.simpleName.toString(), rootView)
        //cleanChildFragments(getChildFragmentManager())

        setValue()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState  == null) {
            f1 = PersonalDistanceScreen.newInstance()
            f2 = PersonalUpdateDistanceScreen.newInstance()
            ReplaceScreen(f1, R.id.container)
        } else {
            childFragmentManager.fragments.map { fragment ->
                when(fragment) {
                    is PersonalDistanceScreen -> f1 = fragment
                    is PersonalUpdateDistanceScreen -> f2 = fragment
                }
            }
        }
        f1?.showOrHiddenCallBack = { showOrHiddenScreen() }
        f2?.showOrHiddenCallBack = { showOrHiddenScreen() }
    }


    private fun setValue() {
        val args = arguments
        setComponent()
    }

    private fun setComponent() {
        //set bold
        tvPoint.setTypeface(null, Typeface.BOLD)

        imvProfile.setOnClickListener {
            val dialogBuilder = iOSDialogBuilder(context)
            dialogBuilder.setTitle("ยืนการทำการการ")
            dialogBuilder.setSubtitle("Enter ยืนยันการขออนุมัติการทำงานล่วงหน้า Below")
            dialogBuilder.setBoldPositiveLabel(true)
            dialogBuilder.setCancelable(false)
            dialogBuilder.setPositiveListener("ยืนยัน") { dialog ->
                //do something with edt.getText().toString();
                dialog.dismiss()

            }
            dialogBuilder.setNegativeListener("ยกเลิก", { dialog ->
                dialog.dismiss()
            })

            dialogBuilder.build().show()
        }

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
                transaction.replace(R.id.container, f2, f2?.TAG)
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE)
                transaction.addToBackStack(f1?.TAG)
                transaction.commit()

            } else if (currentFragment is PersonalUpdateDistanceScreen) {
                val transaction = manager.beginTransaction()
                transaction.setCustomAnimations(
                        R.anim.translate_from_right, R.anim.translate_to_left,
                        R.anim.translate_from_left, R.anim.translate_to_right
                )
                transaction.replace(R.id.container, f1, f1?.TAG)
                transaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_NONE)
                while (childFragmentManager.getBackStackEntryCount() > 0) {
                    childFragmentManager.popBackStack()
                    break
                }
                transaction.commit()
            }
        }
    }

    /**
     * This is necessary to have a clean ChildFragmentManager, old fragments might be called otherwise
     * @param childFragmentManager
     */
    private fun cleanChildFragments(childFragmentManager: FragmentManager) {
        val childFragments = childFragmentManager.fragments
        if (childFragments != null && !childFragments.isEmpty()) {
            val ft = childFragmentManager.beginTransaction()
            for (fragment in childFragments) {
                ft.remove(fragment)
            }
            ft.commit()
        }
    }





}