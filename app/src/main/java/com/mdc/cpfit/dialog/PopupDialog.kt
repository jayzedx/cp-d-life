package com.mdc.cpfit.dialog

import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.mdc.cpfit.R
import android.view.MotionEvent
import android.view.ViewConfiguration
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mdc.cpfit.util.ImageUtil
import kotlinx.android.synthetic.main.dialog_alert.*
import android.widget.RelativeLayout
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition


class PopupDialog : DialogFragment(), View.OnTouchListener {


    private val TAG = PopupDialog::class.java.simpleName
    lateinit var rootView: View

    private var textHead: String? = null
    private var textTitle: String? = null
    private var textDetail: String? = null
    private var image: Any? = null


    companion object {
        fun newInstance(): PopupDialog {
            val fragment = PopupDialog()
            val args = Bundle()
//            args.putParcelable(MsgProperties.ARGUMENT, Parcels.wrap(bitmap))
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.PopupDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        rootView = inflater!!.inflate(R.layout.dialog_alert, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.setOnTouchListener(this)
        btnOk.setOnClickListener { dismiss() }
        setValue()
    }

    private fun setValue() {
        //set text
        textHead?.apply {
            tvHead.text = this
        } ?: apply { tvHead.visibility = View.GONE }

        textTitle?.apply {
            tvTitle.text = this
        } ?: apply { tvTitle.visibility = View.GONE }

        textDetail?.apply {
            tvDetail.text = this
        } ?: apply { tvDetail.visibility = View.GONE }


        if (image == null) {
            val marginTop = context?.resources?.getDimension(R.dimen.MarginSpaceXL)!!.toInt()
            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, marginTop, 0, 0)
            viewImage.visibility = View.GONE
        } else {



            Glide.with(context!!)
                    .asBitmap()
                    .load(image)
                    .into(object: SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            //set sizing of image
                            val notOvalSize = context?.resources?.getDimension(R.dimen._42sdp)!!.toInt()
                            val ovalSize = context?.resources?.getDimension(R.dimen._55sdp)!!.toInt()
                            var width = resource.width
                            var height = resource.height

                            var params = if (height == width) {
                                RelativeLayout.LayoutParams(ovalSize, ovalSize)
                            } else {
                                RelativeLayout.LayoutParams(notOvalSize, notOvalSize)
                            }
                            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
                            imvTopic.setLayoutParams(params)
                            imvTopic.setImageBitmap(resource)
                        }

                    })


        }
    }


    fun setTextHead(text: String = "") {
        textHead = text
    }

    fun setTextTitle(text: String = "") {
        textTitle = text
    }

    fun setTextDetail(text: String = "") {
        textDetail = text
    }

    fun setImageHead(drawable: Int) {
        image = drawable
    }

    fun setImageHead(uri: Uri) {
        image = uri
    }

    override fun onTouch(view: View?, e: MotionEvent?): Boolean {
        val x = e?.x?.toInt()
        val y = e?.y?.toInt()
        x?.let {
            if (!inViewInBounds(viewContent, x!!, y!!) && !inViewInBounds(viewImage, x!!, y!!)) {
                dismiss()
                return true
            }
        } ?: return false

        return false
    }


    var outRect = Rect()
    var location = IntArray(2)
    private fun inViewInBounds(view: View, x: Int, y: Int): Boolean {
        view.getDrawingRect(outRect)
        view.getLocationOnScreen(location)
        outRect.offset(location[0], location[1])
        return outRect.contains(x, y)
    }
}
