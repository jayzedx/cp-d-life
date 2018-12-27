package com.mdc.cpfit.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.mdc.cpfit.R
import com.mdc.cpfit.connections.APIService
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ActivityUnit
import com.mdc.cpfit.util.sharepreferrent.ConfigShare
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.progress_simple.*
import retrofit2.Response


class PersonalActivity : ActivityUnit() {
    val TAG = PersonalActivity::class.java.simpleName
    var disposableLogin: ArrayList<Disposable>? = ArrayList()
    lateinit var dialog: DialogBase
    lateinit var onProgress: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        containView = R.id.contian_view
        dialog = DialogBase(this)

        onProgress = findViewById(R.id.onProgress)
        Glide.with(this).asGif().load(R.drawable.progress).into(gifFile)

        onProgress.visibility = View.VISIBLE
        onLogin()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableLogin?.forEach { it.dispose() }
    }

    private fun onLogin() {
        val phoneNumber = ConfigShare.getShareConfig(ConfigShare.phoneNumber) as String

        //Mockup
        val i = Intent(baseContext, OTPActivity::class.java)
        startActivity(i)
       //Remove activity
        finish()


//        disposableLogin?.add(APIService.instance.requestLogin(phoneNumber).subscribe(
//                { res: Response<LoginModel>? ->
//                    var res = res?.body()
//                    var errorCode = Integer.parseInt(res!!.head.errorCode)
//
//                    when (errorCode) {
//                        0 -> {
//                            checkAccountAPI()
//
//                            ConfigShare.addShareConfig(ConfigShare.tokenLogin, res!!.body.token)
//                            ConfigShare.addShareConfig(ConfigShare.accountId, res!!.body.accountId)
//                            ConfigShare.addShareConfig(ConfigShare.loginSuccess, true)
//
//                            //Remove activity
//                        }
//                        else -> {
//                            val i = Intent(baseContext, OTPActivity::class.java)
//                            startActivity(i)
//                            //Remove activity
//                            finish()
//                        }
//                    }
//
//                }, { e ->
//
//            dialog.DialogSimple("แจ้งเตือน",  getString(R.string.error_api61), object : DialogBase.OnClickDialogSimple {
//                override fun onClickOK() {
//
//                }
//            })
//
//
//            Logging.e(TAG + " Respond error", e.toString())
//
//        }))
    }


    private fun checkAccountAPI() {
//
//        disposableLogin?.add(APIService.instance.checkAccountAPI()
//            .subscribe({ res: Response<CheckAccountModel>? ->
//                var res = res?.body()
//                var errorCode = Integer.parseInt(res!!.head.errorCode)
//
//                when (errorCode) {
//                    0 -> {
//                        finish()
//                        ConfigShare.addShareConfig(ConfigShare.codeActive, res!!.body.active)
//                        val i = Intent(baseContext, MainContainActivity::class.java)
//                        startActivity(i)
//                    }
//                    else -> {
//                        dialog.DialogSimple(getString(R.string.noti_alert),  getString(R.string.error_api62), object : DialogBase.OnClickDialogSimple {
//                            override fun onClickOK() {
//
//
//                            }
//                        })
//                    }
//
//
//                }
//            },
//            { e ->
//
//                dialog.DialogSimple("แจ้งเตือน", getString(R.string.error_api63), object : DialogBase.OnClickDialogSimple {
//                    override fun onClickOK() {
//
//
//                    }
//                })
//
//            }))

    }


}

