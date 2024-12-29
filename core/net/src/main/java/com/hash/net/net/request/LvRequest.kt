package com.hash.net.net.request

import com.hash.net.net.LvHttp
import com.hash.net.net.error.ErrorKey
import com.hash.net.net.response.LvResponse
import com.hash.net.net.response.ResultState

/**
 * @name Request
 * @package com.hash.net.net.launch
 * @author 345 QQ:1831712732
 * @time 2024/12/23 23:23
 * @description 请求发起类，返回自定义的数据类型
 */
class LvRequest<T>(
    private val iResponse: suspend () -> T
) {

    suspend fun launch(lvResponse: LvResponse<T>.() -> Unit) {
        val r = LvResponse<T>()
        lvResponse.invoke(r)
        tryCatch(iResponse, r)
    }

    suspend fun launch(): T? = tryCatch(iResponse).t


    private suspend fun tryCatch(block: suspend () -> T): LvResponse<T> =
        tryCatch(block, LvResponse())


    private suspend fun <T> tryCatch(
        block: suspend () -> T,
        lvResponse: LvResponse<T>
    ): LvResponse<T> {
        var result: T? = null
        try {
            lvResponse.dispatchStateEvent(ResultState.BeginState())

            result = block.invoke()
            lvResponse.t = result

            lvResponse.dispatchStateEvent(
                ResultState.SuccessState(
                    result, 0, "IResponse is not implemented"
                )
            )
        } catch (e: Exception) {
            lvResponse.dispatchStateEvent(ResultState.ErrorState(null, e))
            // 自动匹配异常
            ErrorKey.entries.forEach {
                if (it.name == e::class.java.simpleName) {
                    LvHttp.getErrorDispose(it)?.error?.let { it(e) }
                }
            }
            LvHttp.getErrorDispose(ErrorKey.AllEexeption)?.error?.invoke(e)
        } finally {
            // 无论成功还是失败都会执行
            lvResponse.dispatchStateEvent(ResultState.EndState())
        }
        return lvResponse
    }
}