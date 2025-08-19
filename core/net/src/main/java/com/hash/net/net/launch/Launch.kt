package com.hash.net.net.launch

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.hash.net.net.request.LvRequest
import com.hash.net.net.response.IResponse
import com.hash.net.net.response.ResultState
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
 * 返回实现 [IResponse] 中的 data 数据
 */


suspend fun <T> launchHttp(block: suspend () -> T): ResultState<T> = LvRequest().request(block)

suspend fun <T> launchApi(block: suspend () -> IResponse<T>): ResultState<T> {
    return LvRequest().requestI(block)
}


fun <T> LifecycleOwner.zipLaunch(
    block: List<suspend () -> T>,
    result: (List<ResultState<T>>) -> Unit
) {
    lifecycleScope.launch {
        val list = arrayListOf<Deferred<ResultState<T>>>()
        block.forEach {
            it
            list.add(async { LvRequest().request(it) })
        }
        val data = list.awaitAll()
        launch(Dispatchers.Main) {
            result.invoke(data)
        }
    }
}

