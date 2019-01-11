package com.mdc.cpfit.screen

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.activity.MainContainActivity
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_signup.*




class SignUpScreen : ScreenUnit() {

    val TAG = LoginScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase


    companion object {
        fun newInstance(): SignUpScreen {
            val fragment = SignUpScreen()
            val args = Bundle()
//            args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(SignUpScreen::class.simpleName.toString(), rootView)
        setValue()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_signup, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
//        type = args?.getString(MsgProperties.PERSON_TYPE, "")!!

        dialog = DialogBase(context)
        setComponents()

    }

    private fun setComponents() {
        tvSignUp.setTypeface(null, Typeface.BOLD)
        tvTitle.setTypeface(null, Typeface.BOLD)
//        tvTitleCompany.setTypeface(null, Typeface.BOLD)
//        tvTitleFirstName.setTypeface(null, Typeface.BOLD)
//        tvTitleLastName.setTypeface(null, Typeface.BOLD)
//        tvTitleEmail.setTypeface(null, Typeface.BOLD)
        checkbox.setTypeface(null, Typeface.BOLD)
        tvBack.setTypeface(null, Typeface.BOLD)

        btnSignUp.setAllCaps(false)

        btnSignUp.setOnClickListener {
            activityMain.startActivityUnit(MainContainActivity::class.java, null)
            activityMain.finish()
        }
        tvBack.setOnClickListener {
            goback(false)
        }
        checkbox.setOnClickListener {
            checkbox.isChecked = !checkbox.isChecked
        }

    }



}