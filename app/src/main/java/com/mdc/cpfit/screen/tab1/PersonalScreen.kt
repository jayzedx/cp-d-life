package com.mdc.cpfit.screen.tab1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import retrofit2.Response



class PersonalScreen : ScreenUnit() {

    val TAG = PersonalScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    lateinit var header: TextView
    lateinit var dialog: DialogBase

    companion object {
        fun newInstance(): PersonalScreen {
            val fragment = PersonalScreen()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PersonalScreen::class.simpleName.toString(), rootView)
        setValue()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_personal, container, false)
        return rootView
    }

    private fun setValue() {
        val args = arguments

    }

}