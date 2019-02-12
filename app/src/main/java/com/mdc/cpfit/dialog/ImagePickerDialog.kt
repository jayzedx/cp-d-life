package com.mdc.cpfit.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdc.cpfit.R
import com.mdc.cpfit.msg.MsgProperties
import kotlinx.android.synthetic.main.dialog_image_picker.*
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Parcelable
import android.support.v4.graphics.TypefaceCompatUtil.getTempFile
import android.provider.MediaStore
import android.support.v4.graphics.TypefaceCompatUtil.getTempFile


class ImagePickerDialog: DialogFragment() {

    private val TAG = ImagePickerDialog::class.java.simpleName
    lateinit var rootView: View

    companion object {
        fun newInstance(): ImagePickerDialog {
            val fragment = ImagePickerDialog()
            val args = Bundle()
            //args.putBoolean(MsgProperties.ARGUMENT, cameraUse)
            fragment.setArguments(args)
            fragment.setStyle(android.app.DialogFragment.STYLE_NO_TITLE, 0)
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        rootView =  inflater!!.inflate(R.layout.dialog_image_picker, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValue()
    }

    private fun setValue() {
        val args = arguments
        viewClose.setOnClickListener {
            dismiss()
        }
    }




}