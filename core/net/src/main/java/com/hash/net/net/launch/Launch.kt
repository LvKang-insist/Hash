package com.hash.net.net.launch

import com.hash.net.net.request.RequestBlockIResponse
import com.hash.net.net.request.RequestBlockObject
import com.hash.net.net.response.IResponse
import com.hash.net.net.response.ResultStateFlow
import kotlinx.coroutines.CoroutineScope
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
 */


/**
 * 异步发起请求
 * @param block 请求的代码块 ,T 自定义的返回数据类型
 * @return [ResultStateFlow] 返回结果状态流
 */
fun <T> asyncHttp(block: suspend () -> T): ResultStateFlow<T> =
    ResultStateFlow(RequestBlockObject(block))

/** 同步发起请求 */
suspend fun <T> syncHttp(block: suspend () -> T): T? =
    ResultStateFlow(RequestBlockObject(block)).sync()

/**
 * 异步发起请求
 * @param block 请求的代码块 ,T: 实现了 [IResponse] 接口的返回数据类型
 * @return [ResultStateFlow] 返回结果状态流
 */
fun <T> asyncApi(block: suspend () -> IResponse<T>): ResultStateFlow<T> =
    ResultStateFlow(RequestBlockIResponse(block))

/** 同步发起请求 */
suspend fun <T> syncApi(block: suspend () -> IResponse<T>): T? =
    ResultStateFlow(RequestBlockIResponse(block)).sync()


/**
 * 同步并发请求
 * @param block 请求的代码块列表
 * @return List<T?> 返回结果列表
 * @description 使用协程的 async 和 awaitAll 来实现并发请求
 * for example:
 *
 * ``` kotlin
 * val list = arrayListOf<suspend () -> HomeListBean>()
 * list.add { wanApi.home(page) }
 * val resultList = zipSyncHttp(block = list)
 * ```
 */
suspend fun <T> CoroutineScope.zipSyncHttp(block: List<suspend () -> T?>): List<T?> {
    val list = arrayListOf<Deferred<T?>>()
    block.forEach { it ->
        list.add(async { syncHttp(it) })
    }
    return list.awaitAll()
}
