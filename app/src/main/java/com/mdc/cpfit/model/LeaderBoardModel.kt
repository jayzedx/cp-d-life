package com.mdc.cpfit.model


import com.google.gson.annotations.SerializedName


data class LeaderBoardModel (@SerializedName("HEAD") var head: HeaderModel,
                                @SerializedName("BODY") var body: ArrayList<LeaderBoardBody?>)

data class LeaderBoardBody(@SerializedName("id") var id: Int,
                              @SerializedName("rank")var rank: Int,
                              @SerializedName("name")var name: String?,
                              @SerializedName("title")var title: String?,
                              @SerializedName("distance")var distanceKm: Float,
                              @SerializedName("trophy")var trophy: ArrayList<LeaderBoardTrophyBody>)

data class LeaderBoardTrophyBody(@SerializedName("id") var id: Int,
                           @SerializedName("image_url")var url: String?)
