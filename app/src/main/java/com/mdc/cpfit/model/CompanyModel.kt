package com.mdc.cpfit.model

import com.google.gson.annotations.SerializedName


data class CompanyModel (@SerializedName("HEAD") var head: HeaderModel
                          , @SerializedName("BODY") var body: ArrayList<CompanyModel>)

data class BodyCompany(@SerializedName("company_id")
                        var companyId: Int,
                        @SerializedName("company_name")
                        var companyName: String)
