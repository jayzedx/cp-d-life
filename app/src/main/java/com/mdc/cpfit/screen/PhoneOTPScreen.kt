package com.mdc.cpfit.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.util.ScreenUnit
import com.mdc.cpfit.util.listener.iNetwork


class PhoneOTPScreen : ScreenUnit() {

    val TAG = PhoneOTPScreen::class.java.simpleName
    var rootView: View? = null
    var type: String = ""
    lateinit var header: TextView
    lateinit var phoneEdt: EditText
    lateinit var sendOtpBtn: ImageView
    lateinit var dialog: DialogBase

    companion object {
        fun newInstance(): PhoneOTPScreen {
            val fragment = PhoneOTPScreen()
            val args = Bundle()
//            args.putString(MsgProperties.PERSON_TYPE, typeBd)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFrangment(PhoneOTPScreen::class.simpleName.toString(), rootView)
        setValue()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.sc_phone_otp, container, false)




        return rootView
    }

    private fun setValue() {
        val args = arguments
//        type = args?.getString(MsgProperties.PERSON_TYPE, "")!!


        dialog = DialogBase(context)
        NetworkCheckStatus(object : iNetwork {
            override fun CannotUseInternet() {

                dialog.DialogRefesh("", "", object : DialogBase.OnClickDialogSimple {
                    override fun onClickOK() {

                    }
                })

            }

            override fun CanUseInternet() {
            }

            override fun NetworkChange(NetworkType: Int) {
            }
        })


//        phoneEdt = rootView?.findViewById(R.id.phoneEdt)!!
//        sendOtpBtn = rootView!!.findViewById(R.id.sendOTPBtn)
//
//        sendOtpBtn.setOnClickListener{
//            ConfigShare.addShareConfig(ConfigShare.phoneNumber, phoneEdt.text.toString())
//            GetLogin()
//        }

    }


    private fun GetLogin() {

//        callApi(APIService.instance.requestLogin(phoneEdt.text.toString().trim())
//
//                .subscribe(
//                        { res: Response<LoginModel>? ->
//                            var res = res?.body()
//                            var errorCode = Integer.parseInt(res!!.head.errorCode)
//                            ConfigShare.addShareConfig(ConfigShare.tokenLogin, res!!.body.token)
//                            ConfigShare.addShareConfig(ConfigShare.accountId, res!!.body.accountId)
//                            when (errorCode) {
//                                0 -> {
//                                    checkAccountAPI()
////                                    ReplaceFragment(PinOTPScreen())
////                                    Logging.d(TAG + " Token", res.body.token)
//                                }
//                                2 -> {
//                                    getRequestOTPAPI()
//                                }
//                                4 -> {
//                                    getRequestOTPAPI()
//                                }
//
//
//                                else -> {
//                                    //dialog.DialogSimple("แจ้งเตือน", res!!.head.errorDesc, object : DialogBase.OnClickDialogSimple {
//                                    dialog.DialogSimple("แจ้งเตือน", getString(R.string.error_api1), object : DialogBase.OnClickDialogSimple {
//                                        override fun onClickOK() {
//
//                                        }
//                                    })
//                                }
//                            }
//
//                        }, { e ->
//
//                    dialog.DialogSimple("แจ้งเตือน", getString(R.string.error_api2), object : DialogBase.OnClickDialogSimple {
//                        override fun onClickOK() {
//
//                        }
//                    })
//
//
//                    Logging.e(TAG + " Respond error", e.toString())
//
//                }))
    }

    private fun getRequestOTPAPI() {

//        callApi(APIService.instance.requestOTPAPI(phoneEdt.text.toString().trim())
//
//                .subscribe({ res: Response<OTPmodel>? ->
//                    var res = res?.body()
//                    var errorCode = Integer.parseInt(res!!.head.errorCode)
//                    when (errorCode) {
//                        0 -> {
//                            ReplaceFragment(PinOTPScreen())
//                        }
//                        else -> {
//                            dialog.DialogSimple("แจ้งเตือน", getString(R.string.error_api3), object : DialogBase.OnClickDialogSimple {
//                                override fun onClickOK() {
//
//
//                                }
//                            })
//                        }
//
//
//                    }
//                },
//                        { e ->
//
//                            dialog.DialogSimple("แจ้งเตือน",  getString(R.string.error_api4), object : DialogBase.OnClickDialogSimple {
//                                override fun onClickOK() {
//
//
//                                }
//                            })
//
//                        }))

    }





    private fun checkAccountAPI() {

//        callApi(APIService.instance.checkAccountAPI()
//
//                .subscribe({ res: Response<CheckAccountModel>? ->
//                    var res = res?.body()
//                    var errorCode = Integer.parseInt(res!!.head.errorCode)
//
//                    when (errorCode) {
//                        0 -> {
//                            activityMain.finish()
//                            ConfigShare.addShareConfig(ConfigShare.codeActive, res!!.body.active)
//                            val i = Intent(context, MainContainActivity::class.java)
//                            startActivity(i)
//                        }
//                        else -> {
//                            dialog.DialogSimple(getString(R.string.noti_alert),  getString(R.string.error_api5), object : DialogBase.OnClickDialogSimple {
//                                override fun onClickOK() {
//
//
//                                }
//                            })
//                        }
//
//
//                    }
//                },
//                        { e ->
//
//                            dialog.DialogSimple("แจ้งเตือน", getString(R.string.error_api6), object : DialogBase.OnClickDialogSimple {
//                                override fun onClickOK() {
//
//
//                                }
//                            })
//
//                        }))

    }


}