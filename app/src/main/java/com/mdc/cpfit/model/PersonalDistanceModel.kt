package com.mdc.cpfit.model

import com.google.gson.annotations.SerializedName


data class PersonalDistanceModel (@SerializedName("HEAD") var head: HeaderModel,
                             @SerializedName("BODY") var body: PersonalDistanceBody)

data class PersonalDistanceBody (@SerializedName("id") var id: Int,
                                 @SerializedName("personal_step") var step: Int,
                                 @SerializedName("personal_km") var km: Float,
                                 @SerializedName("company_step") var companyStep: Int,
                                 @SerializedName("company_km") var companyKm: Float,
                                 @SerializedName("company_step_goal") var companyStepGoal: Int,
                                 @SerializedName("company_km_goal") var companyKmGoal: Float,
                                 @SerializedName("total_step") var totalStep: Int,
                                 @SerializedName("total_km") var totalKm: Float,
                                 @SerializedName("total_step_goal") var totalStepGoal: Int,
                                 @SerializedName("total_km_goal") var totalKmGoal: Float)


/**
 * For PersonalDistanceAdapter only
 */

data class PersonalDistanceAdapterModel (var title: String,
                                        var step: Int,
                                        var km: Float,
                                        var stepGoal: Int?,
                                        var kmGoal: Float?)
