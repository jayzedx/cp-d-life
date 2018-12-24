package com.mdc.healthmate.util

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import org.json.JSONObject
import com.google.gson.JsonParser
import com.mdc.cpfit.model.HeaderModel


class JsonUtil private constructor() {
    private var gson = GsonBuilder().setPrettyPrinting().create();

    private object Holder {
        val INSTANCE = JsonUtil()
    }

    companion object {

        val instance: JsonUtil by lazy { Holder.INSTANCE }
    }

    fun getJson(res: ResponseBody?): String? {
        val parser = JsonParser()
        val jsonObj = parser.parse(res?.string()).getAsJsonObject()
        var gson = GsonBuilder().setPrettyPrinting().create()
        return gson?.toJson(jsonObj)
    }


    fun getErrorCode(res: String?): HeaderModel? {
        var headJson = JSONObject(res)
        var head = headJson.getJSONObject("HEAD").toString()

        return gson?.fromJson(head, HeaderModel::class.java)
    }


}