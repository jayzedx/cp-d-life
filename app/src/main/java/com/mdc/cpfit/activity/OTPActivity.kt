package com.mdc.cpfit.activity

import android.os.Bundle
import com.mdc.cpfit.R
import android.app.Activity
import android.content.*
import android.widget.EditText
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.screen.PhoneOTPScreen
import com.mdc.cpfit.util.ActivityUnit
import java.lang.Exception


class OTPActivity : ActivityUnit() {

    val TAG = OTPActivity::class.java.simpleName

    lateinit var phoneEdt: EditText
    lateinit var code: EditText
    lateinit var PERSON_TYPE: String

    private val REQUEST_CODE_PHONE_NUMBER_HINT = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        containView = R.id.contian_view
        ReplaceFragment(PhoneOTPScreen.newInstance())
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

    }

//    private fun connectSmsRetrieverClient(activity: Activity) {
//        val client = SmsRetriever.getClient(activity);
//        client.startSmsRetriever()
//                .addOnSuccessListener(object : OnSuccessListener<Void> {
//                    override fun onSuccess(p0: Void?) {
//
//                    }
//                }).addOnFailureListener(object : OnFailureListener {
//                    override fun onFailure(p0: Exception) {
//
//                    }
//                }
//
//
//                );
//    }
//
//    private fun registerSmsReceiver() {
//        var intentFilter = IntentFilter();
//        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
//        registerReceiver(smsBroadcastReceiver, intentFilter);
//    }
//
//    private fun unregisterSmsReceiver() {
//        unregisterReceiver(smsBroadcastReceiver);
//    }
//
//    private val smsBroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(p0: Context?, p1: Intent?) {
//            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
//                val extras = intent.getExtras();
//                if (extras != null) {
//                    val status = extras.getParcelable<Status>(SmsRetriever.EXTRA_STATUS);
//                    if (status != null) {
//                        when (status.getStatusCode()) {
//                            CommonStatusCodes.SUCCESS -> {
//                                val message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE)
//                            }
//                        // Do something when success
//                            CommonStatusCodes.TIMEOUT -> {
//                            }
//                        // Do something when timeout
//                        }
//                    }
//                }
//            }
//        }
//
//    }


}
