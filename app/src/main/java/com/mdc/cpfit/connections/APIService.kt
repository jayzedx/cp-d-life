package com.mdc.cpfit.connections

import com.google.android.gms.maps.model.LatLng
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mdc.cpfit.connections.RespondListener
import com.mdc.cpfit.dialog.DialogBase
import com.mdc.cpfit.model.*
import com.mdc.cpfit.msg.MsgProperties
import com.mdc.cpfit.util.Contextor
import com.mdc.cpfit.util.Logging
import com.mdc.cpfit.util.sharepreferrent.ConfigShare
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by suphadate_noy on 3/29/18.
 */
open class APIService {
    init {
        println("This ($this) is a singleton")
    }

    private object Holder {
        val INSTANCE = APIService()
    }

    companion object {
        lateinit var retrofit: Retrofit

        val instance: APIService by lazy { Holder.INSTANCE }
    }

    private fun getService(): IService {
        retrofit = Retrofit.Builder().baseUrl(MsgProperties.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())

                .build()

        return retrofit.create(IService::class.java!!)

    }

    private fun getServiceNoCovert(): IService {
        retrofit = Retrofit.Builder().baseUrl(MsgProperties.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(createClient())

                .build()

        return retrofit.create(IService::class.java!!)

    }

    /**
    internal var OKHttp: Create? = null internal
    var with: client? = null internal
    var Interceptor: Logging? = null.
     *
    internal var production: On? = null internal
    var have: not? = null internal
    var interceptor: add? = null
     *
     * @ return OkHttpClient
     */
    private fun createClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)


        return client.build()
    }

    private fun createClientAddTokenHeader(): OkHttpClient {
        val client = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
        client.networkInterceptors().add(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
                var originalRequest = chain?.request();
                if (!"/posts".contains(originalRequest?.url().toString())) {
                    return chain!!.proceed(originalRequest);
                }

                var token = ConfigShare.getShareConfig(ConfigShare.tokenLogin) as String

                var newRequest = originalRequest?.newBuilder()
                        ?.header("Authorization", token)
                        ?.build();

                return chain?.proceed(newRequest)!!
            }
        })

        return client.build()
    }

    fun requestConfigApp(): Observable<Response<ConfigServerModel>> {
        var obb = getService()
                .requestConfigApp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())

        return obb

    }



}