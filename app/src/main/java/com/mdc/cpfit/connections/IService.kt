package com.mdc.cpfit.connections


import com.google.gson.JsonObject
import com.mdc.cpfit.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.POST


/**
 * Created by suphadate_noy on 3/29/18.
 */
interface IService {

    @GET("/config")
    fun requestConfigApp(): Observable<Response<ConfigServerModel>>
}