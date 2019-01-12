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
import com.mdc.cpfit.util.view.MyPasswordTransformationMethod
import kotlinx.android.synthetic.main.sc_forgot_password.*


class ForgotPasswordScreen : ScreenUnit() {

    val TAG = ForgotPasswordScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase


    companion object {
        fun newInstance(): ForgotPasswordScreen {
            val fragment = ForgotPasswordScreen()
            val args = Bundle()
//            args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(ForgotPasswordScreen::class.simpleName.toString(), rootView)
        setValue()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_forgot_password, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
//        type = args?.getString(MsgProperties.PERSON_TYPE, "")!!

        dialog = DialogBase(context)
        setComponents()

    }

    private fun setComponents() {
        tvForgotPassowrd.setTypeface(null, Typeface.BOLD)
        tvTitle.setTypeface(null, Typeface.BOLD)
        checkbox.setTypeface(null, Typeface.BOLD)
        tvBack.setTypeface(null, Typeface.BOLD)
        btnSend.setAllCaps(false)


        btnSend.setOnClickListener {
            activityMain.startActivityUnit(MainContainActivity::class.java, null)
            activityMain.finish()
        }
        tvBack.setOnClickListener {
            goback(false)
        }
    }


}