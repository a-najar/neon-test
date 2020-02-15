package com.neon

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * @name Neon
 * Copyrights (c) 2020-02-15 Created By Ahmad Najar
 **/


private val neonHttpClient = OkHttpClient()


@Throws(NullPointerException::class)
inline fun <reified T> get(url: String): T {
    val dataRequest = requestDataAsJson(url) ?: throw NullPointerException()
    return Gson().fromJson<T>(dataRequest, object : TypeToken<T>() {}.type)
}


@Throws(NullPointerException::class)
fun requestDataAsJson(url: String): String? {
    val request = Request.Builder()
        .get()
        .url(url)
    return neonHttpClient.newCall(request.build()).execute().body?.string()
}