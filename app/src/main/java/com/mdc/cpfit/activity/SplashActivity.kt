package com.mdc.cpfit.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mdc.cpfit.connections.APIService
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import io.reactivex.disposables.Disposable
import retrofit2.Response
import com.mdc.cpfit.model.*
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.util.Logging
import com.mdc.cpfit.util.sharepreferrent.ConfigServer
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class SplashActivity : AppCompatActivity() {
    var disposable: ArrayList<Disposable>? = ArrayList()
    lateinit var disposableProvince: Disposable
    val TAG = SplashActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initData()

    }

    private fun initData() {


        //Mockup
        val i = Intent(applicationContext, PersonalActivity::class.java)
        startActivity(i)
        finish()
        /*
        val background = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
//                    onGetConfig()
//                    onGetProvice()
                    // After 5 seconds redirect to another intent
                    //Remove activity

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }


        }
        // start thread
        background.start()
        */
    }

    public override fun onDestroy() {
        super.onDestroy()

        disposable?.forEach {
            it.dispose()
        }

    }

    /*
    private fun onGetProvice() {
        disposable?.add(APIService.instance.requestProvince()
                .subscribe({ res: Response<ProvinceModel>? ->

                    var res = res?.body()
                    var errorCode = Integer.parseInt(res!!.head.errorCode)
                    if (errorCode == 0) {
                        ConfigServer.instance.arrayProvince?.addAll(res.body)
                    }
                }, { e ->
                    Logging.e("Respond error", e.toString())
                }))

    }


    private fun onGetConfig() {

        disposable?.add(APIService.instance.requestConfigApp()
                .subscribe(
                        { res: Response<ConfigServerModel>? ->
                            var res = res?.body()
                            var errorCode = Integer.parseInt(res!!.head.errorCode)

                            if (errorCode == 0) {

                                ConfigServer.instance.timeOut = res.body.time.description
                                ConfigServer.instance.patientProfilePathImage = MsgProperties.URL + res.body.patientProfilePath.description
                                ConfigServer.instance.systemStatusOnOff = res.body.systemStatus.description
                                ConfigServer.instance.defaultMap = res.body.defaultMap.description
                                ConfigServer.instance.accountProfilePathImage = MsgProperties.URL + res.body.accountProfilePath.description
                                ConfigServer.instance.certNursePath = MsgProperties.URL + res.body.certNursePath.description
                                ConfigServer.instance.caseInfoPath = MsgProperties.URL + res.body.caseInfoPath.description

                                ConfigServer.instance.agreementNurse = MsgProperties.URL + res.body.agreemenNurse.description
                                ConfigServer.instance.agreement = MsgProperties.URL + res.body.agreement.description
                                ConfigServer.instance.manualNurse = MsgProperties.URL + res.body.manualNurse.description
                                ConfigServer.instance.manual = MsgProperties.URL + res.body.manual.description

                                ConfigServer.instance.bankInfo = MsgProperties.URL + res.body.bankInfo.description
                                ConfigServer.instance.paymentInfoPath = MsgProperties.URL + res.body.paymentInfoPath.description
                                ConfigServer.instance.packageInfoPath = MsgProperties.URL + res.body.packageInfoPath.description
                                ConfigServer.instance.bankInfoPath = MsgProperties.URL + res.body.bankInfoPath.description
//                                if (res.body.systemStatus.description.equals("1")) {
                                val i = Intent(applicationContext, PersonnelActivity::class.java)
                                startActivity(i)
                                finish()

//                                } else {
//                                    DialogBase(this@SplashActivity).DialogSimple(false,"แจ้งเตือน"
//                                            , "ขออภัย แอปพลิเคชันนี้กำลังปิดปรับปรุง เราจะเปิดให้บริการในไม่ช้า"
//                                            , object : DialogBase.OnClickDialogSimple {
//                                        override fun onClickOK() {
//                                            finish()
//
//                                        }
//                                    })
//                                }
                            } else {
                                DialogBase(this@SplashActivity).DialogSimple(false, "แจ้งเตือน"
                                        , res.head.errorDesc!!
                                        , object : DialogBase.OnClickDialogSimple {
                                    override fun onClickOK() {
                                        finish()
                                    }


                                })
                            }
                        }, { e ->
                    Logging.e("Respond error", e.toString())
                    DialogBase(this@SplashActivity).DialogRefesh("แจ้งเตือน"
                            , e.toString()
                            , object : DialogBase.OnClickDialogSimple {
                        override fun onClickOK() {


                            intnitData()
                        }


                    })

                }))



        disposable?.add(APIService.instance.requestSex()

                .subscribe(
                        { res: Response<SexModel>? ->
                            var res = res?.body()
                            var errorCode = Integer.parseInt(res!!.head.errorCode)
                            if (errorCode == 0) {
                                ConfigServer.instance.arraySex?.clear()
                                ConfigServer.instance.arraySex?.add(DataSex(0, getString(R.string.selete_sex)))
                                ConfigServer.instance.arraySex?.addAll(res.body.data)


                            }
                        }, { e ->
                    Logging.e(TAG + "Respond error", e.toString())

                }))

    }
    */

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

}

