package com.mdc.cpfit.connections

import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*


/**
 * Created by suphadate_noy on 3/29/18.
 */
interface CallbackListener<T> {
    fun onResponse(res: T, retrofit: Retrofit)

    fun onBodyError(responseBodyError: ResponseBody)
    fun onBodyErrorIsNull()
    fun onFailure(t: Throwable)


}



