package com.hash.net.api


import com.hash.bean.home.KyItemList
import com.hash.net.NetConstants
import com.hash.net.response.WanResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * @name KyApiService
 * @package com.hash.net.api
 * @author 345 QQ:1831712732
 * @time 2024/12/19 23:46
 * @description
 */
interface KyApiService {

    companion object {
        /** 首页推荐列表 */
        const val HOMEPAGE_RECOMMEND_URL = "${NetConstants.BASE_URL}api/v5/index/tab/allRec"
    }

    /** 每日推荐 */
    @GET
    suspend fun getAllRec(@Url url: String): KyItemList

    /** 精选 */
    @GET("api/v5/index/tab/feed")
    suspend fun getFeed(): KyItemList

    /** 首页文章列表 */
    @GET("/article/list/{page}/json")
    fun homeList(@Path("page") page: Int): WanResponse<String>

}