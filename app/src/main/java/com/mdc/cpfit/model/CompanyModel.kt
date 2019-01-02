package com.mdc.cpfit.model

import com.google.gson.annotations.SerializedName


data class CompanyModel (@SerializedName("HEAD") var head: HeaderModel,
                         @SerializedName("BODY") var body: ArrayList<CompanyBody>)

data class CompanyBody(@SerializedName("company_id") var companyId: Int,
                       @SerializedName("company_name") var companyName: String)
