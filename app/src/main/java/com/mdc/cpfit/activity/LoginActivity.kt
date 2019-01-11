package com.mdc.cpfit.activity

import android.os.Bundle
import com.mdc.cpfit.R
import com.mdc.cpfit.screen.LoginScreen
import com.mdc.cpfit.util.ActivityUnit


class LoginActivity : ActivityUnit() {

    val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        containView = R.id.contian_view
        ReplaceFragment(LoginScreen.newInstance())
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

    }

}
