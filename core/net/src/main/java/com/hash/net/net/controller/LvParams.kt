package com.hash.net.net.controller

import android.app.Application
import com.google.gson.Gson
import com.hash.net.net.converter.LvDefaultConverterFactory
import com.hash.net.net.error.ErrorKey
import com.hash.net.net.error.ErrorValue
import com.hash.net.net.interceptor.CacheInterceptor
import com.hash.net.net.interceptor.LogInterceptor
import com.hash.net.net.utils.HTTPSCerUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import java.util.Collections
import java.util.concurrent.TimeUnit


class LvParams {
    lateinit var baseUrl: String
    lateinit var appContext: Application
    var connectTimeOut: Long = 10
    var readTimeOut: Long = 10
    var writeTimeOut: Long = 30
    var isLog = false
    var isCache = false

    /** 请求成功 code */
    var successCode: IntArray = intArrayOf()

    var cacheSize: Long = 1024 * 1024 * 20
    var interceptors = arrayListOf<Interceptor>()
    val errorDisposes: MutableMap<ErrorKey, ErrorValue> = mutableMapOf()
    var cerResId: Int = -1;

}