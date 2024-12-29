package com.hash.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Date

/**
 * @name HeaderInterceptor
 * @package com.hash.net.interceptor
 * @author 345 QQ:1831712732
 * @time 2024/12/26 22:35
 * @description
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().apply {
            header("model", "Android")
            header("If-Modified-Since", "${Date()}")
            header("User-Agent", System.getProperty("http.agent") ?: "unknown")
        }.build()
        return chain.proceed(request)
    }

}