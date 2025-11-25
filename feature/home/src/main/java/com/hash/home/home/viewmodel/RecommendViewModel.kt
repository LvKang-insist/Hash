package com.hash.home.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hash.bean.home.HomeListBean.HomeListItem
import com.hash.common.IApp
import com.hash.net.api.api
import com.hash.net.net.launch.download
import com.hash.net.net.launch.request
import kotlinx.coroutines.Job
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

    private val _data by lazy { MutableLiveData<MutableList<HomeListItem>>() }
    val data: LiveData<MutableList<HomeListItem>> = _data

    // 当前下载任务的 Job，用于取消
    private var downloadJob: Job? = null

    fun getData() {
        viewModelScope.launch {
            request { api.homeList(0) }
                .onBody {
//                    it.data().datas.forEach {
//                    println("getData1: ${it}  ${Thread.currentThread()}")
//                    }
                }
                .onNullData {

                }.onErrorCode { code, body ->
//                    println("getData-ErrorCode: $code  ${body?.data()?.datas?.size} ")
                }.enqueue()
        }
    }

    fun down() {

        val dir = IApp.instant.getExternalFilesDir(null) ?: IApp.instant.filesDir
        println("down-dir: ${dir.path} ")

        // 若已有下载任务，先取消（避免并发写同一个文件）
        downloadJob?.cancel()
        downloadJob = null

        // 真正的断点续传示例：download 接收 existingLength，根据它构造 Range 头
        download { existingLength ->
            val range = if (existingLength > 0) "bytes=$existingLength-" else null
            println("down-existingLength: $existingLength , range=$range ")
            api.downFile(range)
        }
            .onBegin { total, isResume ->
                println("down-onBegin: total=$total bytes, isResume=$isResume ---- ${(total.toFloat() / 1024 / 1024)} MB")
            }
            .onProgress {
                println("down-onProgress: $it ")
            }
            .onEnd {
                println("down-onEnd: ${it.toString()} ")
                downloadJob = null
            }
            .onError {
                println("down-onError: ${it.message} ")
                downloadJob = null
            }
            // 启用断点续传：enableBreakpointResume = true
            .enqueue(
                dir.path,
                "p7.apk",
                viewModelScope,
                enableBreakpointResume = false,
                allowDuplicateFileName = false
            )
            .also { job ->
                downloadJob = job
            }
    }

    /**
     * 取消当前下载
     */
    fun cancelDown() {
        downloadJob?.cancel()
        downloadJob = null
    }

}