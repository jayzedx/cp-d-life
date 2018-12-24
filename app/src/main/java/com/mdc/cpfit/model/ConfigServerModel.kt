package com.mdc.cpfit.model

import com.google.gson.annotations.SerializedName


data class ConfigServerModel(@SerializedName("HEAD") var head: HeaderModel
                             , @SerializedName("BODY") var body: ConfigDataModel)


data class ConfigDataModel(@SerializedName("time") var time: descriptionModel
                           , @SerializedName("account_profile_path") var accountProfilePath: descriptionModel
                           , @SerializedName("patient_profile_path") var patientProfilePath: descriptionModel
                           , @SerializedName("case_info_path") var caseInfoPath: descriptionModel
                           , @SerializedName("cert_nurse_path") var certNursePath: descriptionModel
                           , @SerializedName("system_status") var systemStatus: descriptionModel
                           , @SerializedName("default_map") var defaultMap: descriptionModel
                           , @SerializedName("agreement") var agreement: descriptionModel
                           , @SerializedName("agreement_nurse") var agreemenNurse: descriptionModel
                           , @SerializedName("manual_nurse_path") var manualNurse: descriptionModel
                           , @SerializedName("manual_tankoon_path") var manual: descriptionModel
                           , @SerializedName("bank_info") var bankInfo: descriptionModel
                           , @SerializedName("payment_info_path") var paymentInfoPath: descriptionModel
                           , @SerializedName("package_info_path") var packageInfoPath: descriptionModel
                           , @SerializedName("bank_info_path") var bankInfoPath: descriptionModel)


data class descriptionModel(@SerializedName("description") var description: String)
