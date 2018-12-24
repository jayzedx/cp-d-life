package com.mdc.cpfit.util.sharepreferrent

import android.provider.Settings
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.GsonBuilder
import com.mdc.cpfit.BuildConfig
import com.mdc.cpfit.util.Contextor
import com.mdc.cpfit.util.sharepreferrent.PreferenceHelper.get
import com.mdc.cpfit.util.sharepreferrent.PreferenceHelper.set


class ConfigShare {
    companion object {

        private val CONFIG = "CONFIG"
        private val DATA = "DATA"
        private var gson = GsonBuilder().setPrettyPrinting().create();

        var phoneNumber = "phoneNumber"
        var deviceType = "deviceType"
        var osVersion = "osVersion"
        var appVersion = "appVersion"
        var tokenLogin = "tokenLogin"
        var tokenFirebase = "tokenFirebase"
        var localization = "localization"
        var deviceID = "deviceID"
        var deviceName = "deviceName"
        var loginSuccess = "loginSuccess"
        var sessionID = "sessionID"
        var personType = "persinalType"
        var accountId = "accountId"
        var codeActive = "codeActive"

        private val prefs = PreferenceHelper.defaultPrefs(Contextor.instance.ContextApp!!)


        fun addShareConfig(key: String, data: Any) {
            prefs[key] = data

        }

        fun removeShareConfig(key: String) {
            prefs.edit().remove(key).commit()
        }

        fun getShareConfig(key: String): Any {

            when (key) {
                "phoneNumber" -> {
                    return prefs[key, ""] as String
                }
                "deviceType" -> {
                    return prefs[key, "android"] as String
                }
                "osVersion" -> {
                    return prefs[key, android.os.Build.VERSION.SDK_INT.toString()] as String
                }
                "appVersion" -> {
                    return prefs[key, BuildConfig.VERSION_NAME] as String
                }
                "tokenLogin" -> {
                    return prefs[key, ""] as String
                }
                "tokenFirebase" -> {
                    return prefs[key, FirebaseInstanceId.getInstance().token ?: ""] as String
                }
                "localization" -> {
                    return prefs[key, "TH"] as String
                }
                "deviceID" -> {
                    return prefs[key, Settings.Secure.getString(Contextor.instance.ContextApp!!.getContentResolver(), Settings.Secure.ANDROID_ID)] as String
                }
                "deviceName" -> {
                    return prefs[key, android.os.Build.MODEL] as String
                }
                "loginSuccess" -> {
                    return  prefs[key, false] as Boolean
                }
                "sessionID" -> {
                    return  prefs[key, ""] as String
                }
                "codeActive" -> {
                    return  prefs[key, 0] as Int
                }
                else->{
                    return  prefs["other", 0] as String
                }
            }

        }
    }


}
