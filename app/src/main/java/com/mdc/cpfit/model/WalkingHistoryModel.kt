package com.mdc.cpfit.model

import com.google.gson.annotations.SerializedName


data class WalkingHistoryModel (@SerializedName("HEAD") var head: HeaderModel,
                                @SerializedName("BODY") var body: ArrayList<WalkingHistoryBody>)

data class WalkingHistoryBody(@SerializedName("walking_history_id") var walkingHistoryId: Int,
                              @SerializedName("month")var month: Int,
                              @SerializedName("weight")var weight: Float,
                              @SerializedName("bmi")var bmi: Float)
