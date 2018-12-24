package com.mdc.cpfit.application

import android.app.Application
import com.mdc.cpfit.R
import com.mdc.cpfit.util.Contextor
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


public class ApplicationMain : Application() {

    override fun onCreate() {
        super.onCreate()
        Contextor.instance.ContextApp = applicationContext

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Prompt-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }


}
