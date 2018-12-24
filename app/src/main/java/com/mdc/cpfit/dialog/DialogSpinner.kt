package com.mdc.cpfit.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase

class DialogSpinner(context: Context) : Dialog(context) {

    fun DialogSimple(title: String, detail: String, listener: DialogBase.OnClickDialogSimple?) {

        this.setContentView(R.layout.dialog_show)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCancelable(true)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvTitle = findViewById(R.id.tv_title) as TextView
        val tvDetail = findViewById(R.id.tv_detail) as TextView
        val btnOk = findViewById(R.id.btn_ok) as TextView

        tvTitle.text = title
        tvDetail.text = detail

        btnOk.setOnClickListener {
            listener?.onClickOK()
            dismiss()
        }

        show()
    }
}