package com.hash.home.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hash.bean.ConstType
import com.hash.bean.home.HomeDataBean
import com.hash.net.net.launch.api
import com.hash.net.wanApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @name RecommendViewModel
 * @package com.hash.home.home.viewmodel
 * @author 345 QQ:1831712732
 * @time 2024/12/21 22:44
 * @description
 */
class RecommendViewModel : ViewModel() {

    var page = 0

    private val _data by lazy { MutableLiveData<MutableList<HomeDataBean>>() }
    val data: LiveData<MutableList<HomeDataBean>> = _data

    fun getData() {
        viewModelScope.launch {
            val allList = mutableListOf<HomeDataBean>()
//            val list = async { api { wanApi.homeList(page) }.launch() }.await()
            val banner = async { api { wanApi.banner() }.launch() }.await()
            banner?.let {
                allList.add(
                    HomeDataBean(type = ConstType.WanItemType.BANNER_LIST_ITEM, banner = it)
                )
            }
//            list?.datas.let {
//                val type = ConstType.WanItemType.HOME_LIST_ITEM
//                it?.forEach { e -> allList.add(HomeDataBean(type = type, homeListItem = e)) }
//            }
            _data.postValue(allList)
        }
    }


}