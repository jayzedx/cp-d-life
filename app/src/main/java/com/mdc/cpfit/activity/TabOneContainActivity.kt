package com.mdc.cpfit.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.mdc.cpfit.R
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.screen.tab1.WalkingHistoryScreen
import com.mdc.cpfit.util.ActivityUnit

class TabOneContainActivity : ActivityUnit() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_cotain)
        containView = R.id.contian_view

        val extras = intent.extras
        var bundle = extras.getBundle(MsgProperties.ARGUMENT)
        var model = bundle.getSerializable(MsgProperties.MODEL)
        var screenIntent = bundle.getString(MsgProperties.SCEEN_INTENT)

        when (screenIntent) {
            MsgProperties.DISTANCE_HISTORY -> {
                ReplaceFragmentNoAnimation(WalkingHistoryScreen.newInstance())

            }
        }

    }
}