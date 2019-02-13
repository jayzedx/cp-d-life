package com.mdc.cpfit.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.mdc.cpfit.msg.MsgProperties
import kotlinx.android.synthetic.main.dialog_image_picker.*
import android.graphics.Bitmap
import android.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mdc.cpfit.R
import org.parceler.Parcels


class ImagePickerDialog: DialogFragment(){
    private val TAG = ImagePickerDialog::class.java.simpleName
    lateinit var rootView: View

    private var imageBitmap: Bitmap? = null

    companion object {
        fun newInstance(bitmap: Bitmap?): ImagePickerDialog {
            val fragment = ImagePickerDialog()
            val args = Bundle()
            args.putParcelable(MsgProperties.ARGUMENT, Parcels.wrap(bitmap))
            fragment.setArguments(args)
            return fragment
        }
    }
//    override fun onTouch(view: View?, e: MotionEvent?): Boolean {
//        if (view == imvPicker) {
//            e?.y?.let {
//                val y = e?.y
//                val top = (view as PhotoView).displayRect.top
//                val bottom = (view as PhotoView).displayRect.bottom
//                if (e?.y!! < top || e?.y!! > bottom) dismiss()
//                return true
//            }
//
//        }
//        return false
//    }

//    override fun onStart() {
//        super.onStart()
//        val dialog = dialog
//        if (dialog != null) {
//            val width = ViewGroup.LayoutParams.MATCH_PARENT
//            val height = ViewGroup.LayoutParams.MATCH_PARENT
//            dialog.window.setLayout(width, height)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      setStyle(android.app.DialogFragment.STYLE_NO_TITLE, 0)
      setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        rootView =  inflater!!.inflate(R.layout.dialog_image_picker, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setValue()
    }

    private fun setValue() {
        val args = arguments
        imageBitmap =  Parcels.unwrap(args?.getParcelable(MsgProperties.ARGUMENT))
        Glide.with(context!!)
                .load(imageBitmap)
                .apply(RequestOptions()
                        .override(imageBitmap!!.width, imageBitmap!!.height)
                )
                .into(imvPicker)

        imvPicker.setOnOutsidePhotoTapListener { dismiss() }
        viewClose.setOnClickListener { dismiss() }
    }





}