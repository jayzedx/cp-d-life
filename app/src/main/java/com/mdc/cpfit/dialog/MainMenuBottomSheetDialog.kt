package com.mdc.cpfit.dialog

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.design.widget.BottomSheetDialogFragment
import android.view.View
import com.mdc.cpfit.R


class MainMenuBottomSheetDialog : BottomSheetDialogFragment() {

    var rootView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return rootView
    }

    fun setContentView(view: View) {
        rootView = view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}