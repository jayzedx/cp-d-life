package com.mdc.cpfit.screen

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
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


        cbAutoFill.setOnClickListener {
            cbAutoFill.isChecked = !cbAutoFill.isChecked
            if (!cbAutoFill.isChecked) {
                edtEmail.setText("")
                hideEditTextAnimation()
            } else  {
                var companys = ConfigServer.instance.arrCompany
                companys?.let {
                    if (it.size > companyId) {
                        edtEmail.setText(it[companyId].companyName)
                    }
                }
                showEditTextAnimation()
            }
        }

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
            //arrCompanyName.add(0, getString(R.string.login_select_company))
            companyId = companys[0].companyId

            for (i in 0 until it.size) {
                arrCompanyName.add(it[i].companyName)
            }
            var adapterProvince = ArrayAdapter<String>(context, R.layout.spinner_item_company, arrCompanyName)
            spnCompany.adapter = adapterProvince
        }

        spnCompany.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(position: AdapterView<*>?) {}
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                var oldCompanyId = companyId
                if (position >= 0) {
                    companyId = companys?.let {
                        if (companys.size > 0) {
                            if (cbAutoFill.isChecked && position != companys.size-1) {
                                edtEmail.setText(companys[position].companyName)
                            }
                            it[position].companyId
                        } else 0
                    }
                } else {
                    companyId = -1
                }
                if (position == companys.size-1) {
                    edtEmail.setText("")
                    hideEditTextAnimation()
                }
                else if(oldCompanyId == companys.size-1 && cbAutoFill.isChecked) showEditTextAnimation()
            }
        }
    }

    private fun hideEditTextAnimation() {
        var animatorSet = AnimatorSet()
//        animatorSet.setDuration(500)
        animatorSet.setInterpolator(AccelerateDecelerateInterpolator())
        animatorSet.addListener (object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                viewInputAutoFill.visibility = View.GONE
            }
        })
        var anim = AnimatorInflater.loadAnimator(context, R.animator.to_right) as AnimatorSet
        anim.setTarget(viewInputAutoFill)
        animatorSet.play(anim)
        animatorSet.start()
    }

    private fun showEditTextAnimation() {
        var animatorSet = AnimatorSet()
//        animatorSet.setDuration(500)
        animatorSet.setInterpolator(AccelerateDecelerateInterpolator())
        animatorSet.addListener (object: Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {
                viewInputAutoFill.visibility = View.VISIBLE
                cbAutoFill.visibility = View.VISIBLE

            }
            override fun onAnimationEnd(animation: Animator?) {}
        })

        var anim = AnimatorInflater.loadAnimator(context, R.animator.from_right) as AnimatorSet
        anim.setTarget(viewInputAutoFill)
        animatorSet.play(anim)
        animatorSet.start()
    }



}