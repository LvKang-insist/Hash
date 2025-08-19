package com.hash.net.net.request

import com.hash.net.net.LvHttp
import com.hash.net.net.error.CodeException
import com.hash.net.net.error.ErrorKey
import com.hash.net.net.response.IResponse
import com.hash.net.net.response.ResultState

/**
 * @name Request
 * @package com.hash.net.net.launch
 * @author 345 QQ:1831712732
 * @time 2024/12/23 23:23
 * @description 请求发起类，返回自定义的数据类型
 */
class LvRequest() {


    suspend fun <T> request(block: suspend () -> T): ResultState<T> {
        var t: ResultState<T>?
        try {
            val invoke = block.invoke()
            t = ResultState.SuccessState(invoke, -9999, "IResponse is not implemented")
        } catch (e: Exception) {
            t = parseError(e)
        }
        return t
    }

    suspend fun <T> requestI(block: suspend () -> IResponse<T>): ResultState<T> {
        var t: ResultState<T>?
        try {
            val invoke = block.invoke()
            t = parseIResponse(invoke)
        } catch (e: Exception) {
            t = parseError(e)
        }
        return t
    }


    fun <T> parseError(e: Exception): ResultState<T> {
        val t: ResultState<T> = ResultState.ErrorState(error = e)
        // 自动匹配异常
        ErrorKey.entries.forEach {
            if (it.name == e::class.java.simpleName) {
                LvHttp.getErrorDispose(it)?.error?.let { it(e) }
            }
        }
        // 如果全局异常启用
        LvHttp.getErrorDispose(ErrorKey.AllException)?.error?.let {
            it(e)
        }
        return t
    }


    fun <T> parseIResponse(response: IResponse<T>): ResultState<T> {
        var t: ResultState<T>?
        val data = response.data()
        val code = response.code()
        val msg = response.message()
        // Code 验证
        if (!LvHttp.getCode().contains(code)) {
            t = ResultState.ErrorState(error = CodeException(code, "code 异常"))
            // Code 异常处理
            LvHttp.getErrorDispose(ErrorKey.ErrorCode)?.error?.invoke(
                CodeException(
                    code,
                    msg
                )
            )
        } else {
            t = ResultState.SuccessState(data, code, msg)
        }
        return t
    }

}