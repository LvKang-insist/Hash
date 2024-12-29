package com.hash.net.net.controller

import com.google.gson.Gson
import com.hash.net.net.converter.LvDefaultConverterFactory
import com.hash.net.net.interceptor.CacheInterceptor
import com.hash.net.net.interceptor.LogInterceptor
import com.hash.net.net.utils.HTTPSCerUtils
import com.hjq.gson.factory.GsonFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit

@Suppress("UNCHECKED_CAST")
class LvController {

    private val apiServiceCache = mutableMapOf<String, Any>()
    private lateinit var retrofit: Retrofit

    fun <T> newInstance(clazz: Class<T>): T {
        if (apiServiceCache[clazz.name] == null) {
            val create = retrofit.create(clazz) as Any
            apiServiceCache[clazz.name] = create
            return create as T
        }
        return apiServiceCache[clazz.name] as T
    }


    private fun createOkhttpBuilder(params: LvParams): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .readTimeout(params.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(params.writeTimeOut, TimeUnit.SECONDS)
            .connectTimeout(params.connectTimeOut, TimeUnit.SECONDS)
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .retryOnConnectionFailure(true)
    }

    private fun createRetrofit(params: LvParams, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(params.baseUrl)
            .client(client)
            .addConverterFactory(LvDefaultConverterFactory.create(GsonFactory.getSingletonGson()))
        .build()
    }

    fun build(params: LvParams): LvController {
        val builder = createOkhttpBuilder(params)
        if (params.cerResId != -1) //验证证书
            HTTPSCerUtils.setCertificate(params.appContext, builder, params.cerResId)

        if (params.isCache) {
            builder.cache(Cache(params.appContext.cacheDir, params.cacheSize))
            builder.addNetworkInterceptor(CacheInterceptor())
        }

        if (params.isLog) {
            builder.addInterceptor(LogInterceptor())
//                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        }
        //设置拦截器
        for (interceptor in params.interceptors) {
            builder.addInterceptor(interceptor)
        }
        retrofit = createRetrofit(params, builder.build())
        return this
    }


}