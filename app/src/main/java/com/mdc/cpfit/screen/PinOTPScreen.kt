package com.mdc.cpfit.screen

import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mdc.cpfit.R
import com.mdc.cpfit.util.KeyboardUtil
import com.mdc.cpfit.util.ScreenUnit
import kotlinx.android.synthetic.main.sc_phone_otp.*


class PinOTPScreen : ScreenUnit() {
    val TAG = PinOTPScreen::class.java.simpleName
    var rootView: View? = null


    companion object {
        fun newInstance(): PinOTPScreen {
            val fragment = PinOTPScreen()
            val args = Bundle()
            //args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_phone_otp, container, false)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PhoneOTPScreen::class.simpleName.toString(), rootView)
        setValue()
    }

    private fun setValue() {
        val args = arguments
        setComponents()
    }

    private fun setComponents() {
        edtPin1.requestFocus()
        val imm = activityMain.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(edtPin1, InputMethodManager.SHOW_IMPLICIT)
        nextChangeOfcusEditext(edtPin1, edtPin2)
        nextChangeOfcusEditext(edtPin2, edtPin3)
        nextChangeOfcusEditext(edtPin3, edtPin4)
    }




    private fun nextChangeOfcusEditext(editNow: EditText, editNext: EditText) {
        editNow.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var char = p0
                char?.let {
                    if (it.length > 0) {
                        editNext.requestFocus()
                        editNow.setSelection(0)
                    }
                    if(it.length == 2) {
                        editNow.setText(char[0].toString())
                    }
                }

            }
        })

        editNext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, count: Int) {

                var char = p0
                char?.let {
                    if (it.length <= 0) {
                        editNow.requestFocus()
                        editNow.setSelection(editNow.text.length)
                    }
                    if(it.length == 2) {
                        editNext.setText(char[0].toString())
                    }
                }

            }
        })
    }



}