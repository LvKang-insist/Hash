package com.hash.home.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hash.common.ext.showToast
import com.hash.net.api.KyApiService
import com.hash.net.kyApi
import com.hash.net.net.launch.api
import com.hash.net.net.launch.apiHttp
import com.hash.net.wanApi
import kotlinx.coroutines.launch

import timber.log.Timber

/**
 * @name RecommendViewModel
 * @package com.hash.home.home.viewmodel
 * @author 345 QQ:1831712732
 * @time 2024/12/21 22:44
 * @description
 */
class RecommendViewModel : ViewModel() {

    var pageUrl = KyApiService.HOMEPAGE_RECOMMEND_URL

    fun recommendList() {
        apiHttp({
            kyApi.getAllRec(pageUrl)
        }) {
            toData {
                Timber.e("toData : ${it.itemList.size}")
            }
        }
    }

    fun wan() {
        viewModelScope.launch {
            api { wanApi.homeList(0) }.launch {
                toData {
                    Timber.e("toData : ${it}")
                }
            }
        }
    }

}