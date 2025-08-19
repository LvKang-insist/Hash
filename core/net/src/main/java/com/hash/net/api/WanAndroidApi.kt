package com.hash.net.api

import com.hash.bean.home.BannerListBean
import com.hash.bean.home.HomeListBean
import com.hash.net.response.WanResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

/**
 * @name WanAndroidApi
 * @package com.hash.net.api
 * @author 345 QQ:1831712732
 * @time 2024/12/30 23:32
 * @description
 */
interface WanAndroidApi {

    /** 鸿蒙专栏 */
    @GET("/banner/json")
    suspend fun banner(): WanResponse<BannerListBean>

    /** 首页文章列表 */
    @GET("/article/list/{page}/json")
    suspend fun homeList(@Path("page") page: Int): WanResponse<HomeListBean>
    suspend fun home(@Path("page") page: Int): HomeListBean

    /** 常用网站 */
    @GET("/friend/json")
    suspend fun friend(): String

    /** 热搜词 */
    @GET("/hotkey/json")
    suspend fun hotKey(): WanResponse<String>

    @GET("/article/top/json")
    suspend fun topList(): WanResponse<Any>

    @Streaming
    @GET("https://files.pythonhosted.org/packages/6b/34/415834bfdafca3c5f451532e8a8d9ba89a21c9743a0c59fbd0205c7f9426/six-1.15.0.tar.gz")
    suspend fun download(): ResponseBody
}