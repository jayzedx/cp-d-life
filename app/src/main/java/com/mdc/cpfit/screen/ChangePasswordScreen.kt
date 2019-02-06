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
import kotlinx.android.synthetic.main.sc_change_password.*


class ChangePasswordScreen : ScreenUnit() {

    val TAG = ChangePasswordScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase


    companion object {
        fun newInstance(): ChangePasswordScreen {
            val fragment = ChangePasswordScreen()
            val args = Bundle()
//            args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(ChangePasswordScreen::class.simpleName.toString(), rootView)
        setValue()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_change_password, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments
//        type = args?.getString(MsgProperties.PERSON_TYPE, "")!!

        dialog = DialogBase(context)
        setComponents()

    }

    private fun setComponents() {
        tvChangePassword.setTypeface(null, Typeface.BOLD)
        tvTitle.setTypeface(null, Typeface.BOLD)
//        tvTitleCompany.setTypeface(null, Typeface.BOLD)
//        tvTitleFirstName.setTypeface(null, Typeface.BOLD)
//        tvTitleLastName.setTypeface(null, Typeface.BOLD)
//        tvTitleEmail.setTypeface(null, Typeface.BOLD)
        tvBack.setTypeface(null, Typeface.BOLD)

        btnSend.setAllCaps(false)

        //edtOldPassword.setTransformationMethod(MyPasswordTransformationMethod())
        //edtPassword.setTransformationMethod(MyPasswordTransformationMethod())
        //edtConfirmPassword.setTransformationMethod(MyPasswordTransformationMethod())



        btnSend.setOnClickListener {
            activityMain.startActivityUnit(MainContainActivity::class.java, null)
            activityMain.finish()
        }
        tvBack.setOnClickListener {
            goback(false)
        }
    }


}