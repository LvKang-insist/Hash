package com.hash.net.api

import com.hash.bean.home.HomeListBean
import com.hash.net.response.WanResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @name WanAndroidApi
 * @package com.hash.net.api
 * @author 345 QQ:1831712732
 * @time 2024/12/30 23:32
 * @description
 */
interface WanAndroidApi {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
        const val BASE_URL_KEY = "wan_android"
    }

    /** 鸿蒙专栏 */
    @GET("/banner/json")
    suspend fun banner(): WanResponse<String>

    /** 首页文章列表 */
    @GET("/article/list/{page}/json")
    suspend fun homeList(@Path("page") page: Int): WanResponse<HomeListBean>

    /** 常用网站 */
    @GET("/friend/json")
    suspend fun friend(): WanResponse<String>

    /** 热搜词 */
    @GET("/hotkey/json")
    suspend fun hotKey(): WanResponse<String>

    @GET("/article/top/json")
    suspend fun topList(): WanResponse<String>
}