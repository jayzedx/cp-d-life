package com.mdc.cpfit.dialog

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.util.ActivityUnit
import com.mdc.cpfit.util.Permission


class DialogBase(context: Context?) : Dialog(context, R.style.MyAlertDialogTheme) {

    interface OnClickDialog {
        fun onClickPositive()
        fun onClickNegative()
    }

    interface OnSetDialog {
        fun onSetDialogContent()
    }

    interface OnClickDialogSimple {
        fun onClickOK()
    }

    fun DialogSimple(title: String, detail: String, listener: OnClickDialogSimple?) {

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

    fun DialogSimple(cancelFlag: Boolean, title: String, detail: String, listener: OnClickDialogSimple?) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.dialog_show)
        setCancelable(cancelFlag)
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

    fun DialogRefesh(title: String, detail: String, listener: OnClickDialogSimple?) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.dialog_refresh)
        setCancelable(true)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val tvTitle = findViewById(R.id.tv_title) as TextView
        val tvDetail = findViewById(R.id.tv_detail) as TextView
        val btnOk = findViewById(R.id.btn_ok) as ImageView
        if (!title.isEmpty())
            tvTitle.text = title
        if (!detail.isEmpty())
            tvDetail.text = detail

        btnOk.setOnClickListener {
            listener?.onClickOK()
            dismiss()
        }

        show()
    }

    fun DialogYesNo(title: String, detail: String, listener: OnClickDialog?) {
        setContentView(R.layout.dialog_select)
        setCancelable(true)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvTitle = findViewById(R.id.tv_title) as TextView
        val tvDetail = findViewById(R.id.tv_detail) as TextView
        val btnOk = findViewById(R.id.btn_ok) as Button
        val btnCancel = findViewById(R.id.btn_cancel) as Button

        tvTitle.text = title
        tvDetail.text = detail

        btnOk.setOnClickListener {
            listener?.onClickPositive()
            dismiss()
        }

        btnCancel.setOnClickListener {
            listener?.onClickNegative()
            dismiss()
        }
        show()
    }

    fun DialogCustom(dialogView: View, listenerSetDialog: OnSetDialog, listener: OnClickDialog?) {
        setContentView(dialogView)
        listenerSetDialog.onSetDialogContent()
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        this.window!!.setGravity(Gravity.TOP)
        setCancelable(true)

        val btnOk = findViewById<Button?>(R.id.btn_ok)
        val btnCancel = findViewById<Button?>(R.id.btn_cancel)



        btnOk?.setOnClickListener {
            listener?.onClickPositive()
            dismiss()
        }

        btnCancel?.setOnClickListener {
            listener?.onClickNegative()
            dismiss()
        }
        show()

    }


    fun DialogCall(activityMain: ActivityUnit,numberPhone : String) {

        setContentView(R.layout.dialog_contact)
        setCancelable(true)
        this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvDetail = findViewById(R.id.tv_detail) as TextView
        val btnOk = findViewById(R.id.btn_ok) as Button
        val btnCancel = findViewById(R.id.btn_cancel) as Button
        tvDetail.text = numberPhone
        btnOk.text = context.getString(R.string.call)
        btnCancel.text = context.getString(R.string.cancel)

        btnOk.setOnClickListener {
            var permission = Permission()
            permission.requestPermission(activityMain, object : Permission.CallBackPermission {
                @SuppressLint("MissingPermission")
                override fun allowPermission() {

                    val intent = Intent(Intent.ACTION_CALL, Uri.fromParts("tel", numberPhone, null))
                    activityMain.startActivity(intent)
                }

                override fun deninePermissiob() {
                    Toast.makeText(context, "Permission denied, can't enable",
                            Toast.LENGTH_SHORT).show();
                }

            }, Manifest.permission.CALL_PHONE)
            dismiss()
        }
        btnCancel.setOnClickListener {
            dismiss()
        }

        show()

    }
}
