package com.mdc.cpfit.model
import com.google.gson.annotations.SerializedName


data class WalkingHistoryReportModel (@SerializedName("HEAD") var head: HeaderModel,
                                @SerializedName("BODY") var body: ArrayList<WalkingHistoryBody>)

data class WalkingHistoryReportBody(@SerializedName("walking_history_report_id") var walkingHistoryReportId: Int,
                              @SerializedName("month")var month: Int,
                              @SerializedName("weight")var weight: Float,
                              @SerializedName("bmi")var bmi: Float)
