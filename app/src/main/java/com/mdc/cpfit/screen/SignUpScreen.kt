package com.mdc.cpfit.screen

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.mdc.cpfit.R
import com.mdc.cpfit.activity.MainContainActivity
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.model.CompanyBody
import com.mdc.cpfit.util.ScreenUnit
import com.mdc.cpfit.util.sharepreferrent.ConfigServer
import kotlinx.android.synthetic.main.sc_signup.*
import android.animation.ObjectAnimator
import android.support.v4.content.ContextCompat
import android.view.animation.AccelerateDecelerateInterpolator


class SignUpScreen : ScreenUnit() {

    val TAG = SignUpScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase

    var arrCompanyName = ArrayList<String>()
    var companyId  = -1

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
//        cbAutoFill.setTypeface(null, Typeface.BOLD)
        tvBack.setTypeface(null, Typeface.BOLD)

        btnSignUp.setAllCaps(false)

        btnSignUp.setOnClickListener {
            IntentFragment(ChangePasswordScreen.newInstance())
        }
        tvBack.setOnClickListener {
            goback(false)
        }
        cbAutoFill.setOnClickListener {
            cbAutoFill.isChecked = !cbAutoFill.isChecked
            if (!cbAutoFill.isChecked) edtEmail.setText("")
            else  {
                var companys = ConfigServer.instance.arrCompany
                companys?.let {
                    if (it.size > companyId) {
                        edtEmail.setText(it[companyId].companyName)
                    }
                }
            }
        }
        cbAgree.setOnClickListener {
            cbAgree.isChecked = !cbAgree.isChecked
            if (!cbAgree.isChecked) {
                btnSignUp.isClickable = false
                btnSignUp.background = ContextCompat.getDrawable(context!!, R.drawable.btn_create_right_disable)
            } else {
                btnSignUp.isClickable = true
                btnSignUp.background = ContextCompat.getDrawable(context!!, R.drawable.btn_create_right)
            }
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
                else if(oldCompanyId == companys.size-1) showEditTextAnimation()
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
                cbAutoFill.visibility = View.GONE
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