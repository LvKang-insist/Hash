package com.hash.net.net.launch

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.hash.net.net.request.LvIRequest
import com.hash.net.net.response.IResponse
import com.hash.net.net.response.LvResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * @name ILaunch
 * @package com.www.net
 * @author 345 QQ:1831712732
 * @time 2020/6/23 21:33
 * @description 请求发起的扩展函数
 * 返回实现 [IResponse] 中的 data 数据
 */

fun <T> api(block: suspend () -> IResponse<T>): LvIRequest<T> {
    return LvIRequest(block)
}

fun <T> ViewModel.api(block: suspend () -> IResponse<T>, lvResponse: LvResponse<T>.() -> Unit) {
    viewModelScope.launch {
        LvIRequest(block).launch(lvResponse)
    }
}

fun <T> AppCompatActivity.api(
    block: suspend () -> IResponse<T>,
    lvResponse: LvResponse<T>.() -> Unit
) {
    lifecycleScope.launch {
        LvIRequest(block).launch(lvResponse)
    }
}

fun <T> LifecycleOwner.zipLaunch(
    block: List<suspend () -> IResponse<T>>,
    result: (List<T?>) -> Unit
) {
    lifecycleScope.launch {
        zipLaunch(this, block, result)
    }
}

fun <T> ViewModel.zipLaunch(
    block: List<suspend () -> IResponse<T>>,
    result: (List<T?>) -> Unit
) {
    viewModelScope.launch {
        zipLaunch(this, block, result)
    }
}

private suspend fun <T> zipLaunch(
    coroutineScope: CoroutineScope,
    block: List<suspend () -> IResponse<T>>,
    result: (List<T?>) -> Unit
) {
    val list = arrayListOf<Deferred<T?>>()
    block.forEach {
        list.add(coroutineScope.async { LvIRequest(it).launch() })
    }
    val data = list.awaitAll()
    result.invoke(data)
}

