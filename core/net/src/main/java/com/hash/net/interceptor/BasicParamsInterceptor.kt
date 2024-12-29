package com.hash.net.interceptor

import android.os.Build
import com.hash.common.ext.screenPixel
import com.hash.common.utils.GlobalUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @name BasicParamsInterceptor
 * @package com.hash.net.interceptor
 * @author 345 QQ:1831712732
 * @time 2024/12/26 22:36
 * @description
 */
class BasicParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url
        val url = originalHttpUrl.newBuilder().apply {
            addQueryParameter("udid", GlobalUtil.getDeviceSerial())
            //针对开眼官方【首页推荐 】api 变动， 需要单独做处理。原因：附加 vc、vn 这两个字段后，请求接口无响应。
            if (!originalHttpUrl.toString().contains("api/v5/index/tab/allRec")) {
                addQueryParameter("vc", GlobalUtil.eyepetizerVersionCode.toString())
                addQueryParameter("vn", GlobalUtil.eyepetizerVersionName)
            }
            addQueryParameter("size", screenPixel())
            addQueryParameter("deviceModel", GlobalUtil.deviceModel)
            addQueryParameter("first_channel", GlobalUtil.deviceBrand)
            addQueryParameter("last_channel", GlobalUtil.deviceBrand)
            addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
        }.build()
        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}