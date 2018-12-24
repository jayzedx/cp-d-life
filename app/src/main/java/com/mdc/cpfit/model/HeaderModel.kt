package com.mdc.cpfit.model

import com.google.gson.annotations.SerializedName


data class HeaderModel(
        @SerializedName("model") val model: String
        , @SerializedName("method") val method: String
        , @SerializedName("error_flag") val errorFlag: String
        , @SerializedName("error_code") val errorCode: String
        , @SerializedName("error_desc") val errorDesc: String)