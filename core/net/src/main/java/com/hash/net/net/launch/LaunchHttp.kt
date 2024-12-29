package com.hash.net.net.launch

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.hash.net.net.request.LvRequest
import com.hash.net.net.response.LvResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


/**
 * @name ILaunch
 * @package com.www.net
 * @author 345 QQ:1831712732
 * @time 2020/6/23 21:33
 * @description 请求发起的扩展函数
 * 返回自定义的请求结果数据
 */

fun <T> apiHttp(block: suspend () -> T): LvRequest<T> {
    return LvRequest(block)
}

fun <T> ViewModel.apiHttp(block: suspend () -> T, lvResponse: LvResponse<T>.() -> Unit) {
    viewModelScope.launch {
        LvRequest(block).launch(lvResponse)
    }
}

fun <T> AppCompatActivity.apiHttp(block: suspend () -> T, lvResponse: LvResponse<T>.() -> Unit) {
    lifecycleScope.launch {
        LvRequest(block).launch(lvResponse)
    }
}

fun <T> LifecycleOwner.zipLaunchHttp(
    block: List<suspend () -> T>,
    result: (List<T?>) -> Unit
) {
    lifecycleScope.launch {
        val list = arrayListOf<Deferred<T?>>()
        block.forEach {
            list.add(async { LvRequest(it).launch() })
        }
        val data = list.awaitAll()
        launch(Dispatchers.Main) {
            result.invoke(data)
        }
    }
}

