package com.mdc.cpfit.screen

import android.R.id.button2
import android.R.id.button1
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.mdc.cpfit.R


class MainMenuBottomSheetDialog : BottomSheetDialogFragment() {

    private var mListener: BottomSheetListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.main_menu, container, false)
        return v
    }

    interface BottomSheetListener {
        fun onButtonClicked(text: String)
    }

}