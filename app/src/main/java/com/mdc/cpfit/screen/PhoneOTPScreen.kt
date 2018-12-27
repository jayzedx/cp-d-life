package com.mdc.cpfit.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.mdc.cpfit.R
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.dialog.SearchableSpinner
import com.mdc.cpfit.model.BodyCompany
import com.mdc.cpfit.model.CompanyModel
import com.mdc.cpfit.util.ScreenUnit
import com.mdc.cpfit.util.listener.iNetwork
import com.mdc.cpfit.util.sharepreferrent.ConfigServer
import com.mdc.cpfit.util.sharepreferrent.ConfigShare
import kotlinx.android.synthetic.main.sc_login.*


class PhoneOTPScreen : ScreenUnit() {

    val TAG = PhoneOTPScreen::class.java.simpleName
    var rootView: View? = null
    lateinit var dialog: DialogBase

    var arrCompanyName = ArrayList<String>()
    var arrCompany = ArrayList<BodyCompany>()
    var companyId  = -1

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
        rootView = inflater?.inflate(R.layout.sc_login, container, false)
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

        setComponents()

        imvSubmit.setOnClickListener{
            ConfigShare.addShareConfig(ConfigShare.phoneNumber, edtPhone.text.toString())
            GetLogin()
            //Mockup
            IntentFragment(PinOTPScreen.newInstance())

        }

    }

    private fun setComponents() {
        initSpinner()
    }

    private fun initSpinner() {
        spnCompany.setTitle(getString(R.string.login_select_company))
        spnCompany.setPositiveButton(getString(R.string.cancel))

        ConfigServer.instance.arrCompany?.let {
            var companys = it
            //arrCompanyName.add(0, getString(R.string.login_select_company))
            for (i in 0 until companys.size) {
                arrCompanyName.add(companys[i].companyName)
            }
            var adapterProvince = ArrayAdapter<String>(context, R.layout.spinner_item_company, arrCompanyName)
            spnCompany.adapter = adapterProvince
        }

        spnCompany.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(position: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                if (position > 0) {
                    companyId = arrCompany?.let {
                        if (it.size > 0) it[position - 1].companyId
                        else 0
                    }
                } else {
                    companyId = -1
                }
            }
        }
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