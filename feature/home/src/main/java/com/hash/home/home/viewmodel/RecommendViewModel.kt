package com.hash.home.home.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hash.bean.home.HomeListBean.HomeListItem
import com.hash.net.net.download.DownResponse
import com.hash.net.net.download.start
import com.hash.net.net.launch.launchApi
import com.hash.net.net.launch.launchHttp
import com.hash.net.wanApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File

/**
 * @name RecommendViewModel
 * @package com.hash.home.home.viewmodel
 * @author 345 QQ:1831712732
 * @time 2024/12/21 22:44
 * @description
 */
class RecommendViewModel : ViewModel() {

    var page = 0

    private val _data by lazy { MutableLiveData<MutableList<HomeListItem>>() }
    val data: LiveData<MutableList<HomeListItem>> = _data

    fun getData() {
        viewModelScope.launch {
            launchApi {
                wanApi.homeList(0)
            }.toData {
                _data.postValue(it?.datas?.toMutableList() ?: mutableListOf())
            }.toError {

            }

        }
    }

    fun download() {
        viewModelScope.launch {
            launchHttp {
                val download = wanApi.download()
                    .start(object : DownResponse("LvHttp", "chebangyang.apk") {
                        override fun create(size: Float) {
                            Log.e("-------->", "create:总大小 ${(size)} ")
                        }

                        @SuppressLint("SetTextI18n")
                        override fun process(process: Float) {
                            Log.e("-------->","$process %")
                        }

                        override fun error(e: Exception) {
                            e.printStackTrace()

                        }

                        override fun done(file: File) {
                            //完成
                            Toast.makeText(this@MainActivity, "成功", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
            }
        }
    }


}