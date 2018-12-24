package com.mdc.cpfit.util.view

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.mdc.cpfit.util.Logging


class ScrollDetect @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : NestedScrollView(context, attrs, defStyleAttr), View.OnTouchListener {

    init {

        setOnTouchListener(this)
    }

    private  var mOnScrollStopStartListener: OnScrollStopStartListener? = null


    interface OnScrollStopStartListener {
        abstract fun onStopScroll()
        abstract fun onScrolling()
    }




    private fun getOnScrollStopStartListenerr(): OnScrollStopStartListener? {
        return mOnScrollStopStartListener
    }

    fun setOnScrollStopStartListener(onScrollStopStartListener: OnScrollStopStartListener?) {
        this.mOnScrollStopStartListener = onScrollStopStartListener
    }


    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        when (event?.getAction()) {
            MotionEvent.ACTION_SCROLL,
            MotionEvent.ACTION_MOVE -> {
                getOnScrollStopStartListenerr()?.onScrolling()
                Logging.e("SCROLL", "ACTION_SCROLL")
            }


            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                getOnScrollStopStartListenerr()?.onStopScroll()
                Logging.e("SCROLL", "SCROLL_STOP")
            }
        }
        return false


    }
}
//- prompt beacon
//- stroke
//- ez prompt svn
//- digital feed
//- Gold spot freewill new theme