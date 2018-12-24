package com.mdc.cpfit.util
import android.content.Context

class Contextor {
    public var ContextApp: Context? = null

    private object Holder {
        val INSTANCE = Contextor()
    }

    companion object {
        val instance: Contextor by lazy { Holder.INSTANCE }
    }

}