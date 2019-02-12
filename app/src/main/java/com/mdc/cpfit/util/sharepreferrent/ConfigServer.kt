package com.mdc.cpfit.util.sharepreferrent

import com.mdc.cpfit.model.CompanyBody


class ConfigServer {
    /*
    var timeOut: String? = "20"
    var accountProfilePathImage: String? = MsgProperties.URL + "/image/account/profile/"
    var patientProfilePathImage: String? = MsgProperties.URL + "/image/patient/profile/"
    var caseInfoPath: String? = MsgProperties.URL + "/image/patient/case/"
    var certNursePath: String? = MsgProperties.URL + "/image/cert/"
    var systemStatusOnOff: String? = "1"
    var defaultMap: String? = "1"

    var agreementNurse: String? = "/files/pdf/agreement/tankoon_agreement_nu.pdf"
    var agreement: String? = "/files/pdf/agreement/tankoon_agreement.pdf"
    var manualNurse: String? = "/files/pdf/manual/manual_tankoon_nu.pdf"
    var manual: String? = "/files/pdf/manual/manual_tankoon.pdf"
    var bankInfo: String? = "/image/bank/bank_info.png"
    var paymentInfoPath: String? = "/image/order/payment_evidence/"
    var packageInfoPath: String? = "/image/package/"
    var bankInfoPath: String? = "/image/bank/"

    var arrayProvince: ArrayList<BodyProvince> = ArrayList()
    var arraySex: ArrayList<DataSex> = ArrayList()
    */

    var agreement: String? = "https://www.apple.com/legal/sla/docs/macOS1014.pdf"


    var arrCompany: ArrayList<CompanyBody> = ArrayList()

    init {
        //company
        //Mockup
        var data = CompanyBody(0, "Freewill Solutions")
        arrCompany.add(data)
        var data2 = CompanyBody(1, "CPF")
        arrCompany.add(data2)
        var data3 = CompanyBody(2, "Other")
        arrCompany.add(data3)
    }


    private object Holder {
        val INSTANCE = ConfigServer()
    }

    companion object {
        val instance: ConfigServer by lazy { Holder.INSTANCE }
    }

}