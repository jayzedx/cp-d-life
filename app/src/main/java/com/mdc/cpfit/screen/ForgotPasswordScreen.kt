package com.mdc.cpfit.screen

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mdc.cpfit.R
import com.mdc.cpfit.activity.MainContainActivity
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import com.mdc.cpfit.util.sharepreferrent.ConfigServer
import com.mdc.cpfit.util.view.MyPasswordTransformationMethod
import kotlinx.android.synthetic.main.sc_forgot_password.*


class ForgotPasswordScreen : ScreenUnit() {

    val TAG = ForgotPasswordScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase

    var arrCompanyName = ArrayList<String>()
    var companyId  = -1

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
        tvBack.setTypeface(null, Typeface.BOLD)
        btnSend.setAllCaps(false)


        btnSend.setOnClickListener {
            activityMain.startActivityUnit(MainContainActivity::class.java, null)
            activityMain.finish()
        }
        tvBack.setOnClickListener {
            goback(false)
        }
        initSpinner()
    }

    private fun initSpinner() {
        spnCompany.setTitle(getString(R.string.login_select_company))
        spnCompany.setPositiveButton(getString(R.string.cancel))

        var companys = ConfigServer.instance.arrCompany

        companys?.let {
//            arrCompanyName.add(0, getString(R.string.login_select_company))
            for (i in 0 until it.size) {
                arrCompanyName.add(it[i].companyName)
            }
            var adapterProvince = ArrayAdapter<String>(context, R.layout.spinner_item_company, arrCompanyName)
            spnCompany.adapter = adapterProvince
        }

        spnCompany.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(position: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                var oldCompanyId = companyId
                if (position > 0) {
                    companyId = companys?.let {
                        if (it.size > 0) {
                            tvEmail.text = it[position].companyName
                            it[position].companyId
                        } else 0
                    }
                } else {
                    companyId = -1
                }
                if (position == companys.size-1) tvEmail.visibility = View.GONE
                else if(oldCompanyId == companys.size-1) tvEmail.visibility = View.VISIBLE
            }
        }
    }



}