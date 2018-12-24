package com.mdc.cpfit.model

import android.provider.Settings
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.annotations.SerializedName
import com.mdc.cpfit.BuildConfig
import com.mdc.cpfit.util.Contextor

data class ConfigModel(

        @SerializedName("phoneNumber")
        var phoneNumber: String = "",
        @SerializedName("deviceType")
        var deviceType: String = "android",
        @SerializedName("osVersion")
        var osVersion: String = android.os.Build.VERSION.SDK_INT.toString(),
        @SerializedName("appVersion")
        var appVersion: String = BuildConfig.VERSION_NAME,
        @SerializedName("tokenLogin")
        var tokenLogin: String = "",
        @SerializedName("tokenFirebase")
        var tokenFirebase: String = FirebaseInstanceId.getInstance().token ?: "",
        @SerializedName("localization")
        var localization: String = "TH",
        @SerializedName("deviceID")
        var deviceID: String = Settings.Secure.getString(Contextor.instance.ContextApp!!.getContentResolver(), Settings.Secure.ANDROID_ID),
        @SerializedName("deviceName")
        var deviceName: String = android.os.Build.MODEL,

        @SerializedName("loginSuccess")
        var loginSuccess: Boolean = false,
        @SerializedName("sessionID")
        var sessionID: String = "",

        @SerializedName("codeActive")
        var codeActive: Int = 0


)
