package com.hash.net.net.request

import com.hash.net.net.LvHttp
import com.hash.net.net.error.CodeException
import com.hash.net.net.error.ErrorKey
import com.hash.net.net.response.IResponse
import com.hash.net.net.response.LvResponse
import com.hash.net.net.response.ResultState

/**
 * @name Request
 * @package com.hash.net.net.launch
 * @author 345 QQ:1831712732
 * @time 2024/12/23 23:23
 * @description 请求发起类，返回实现 [IResponse] 接口的数据类型
 */
class LvIRequest<T>(
    private val iResponse: suspend () -> IResponse<T>
) {

    suspend fun launch(lvResponse: LvResponse<T>.() -> Unit) {
        val r = LvResponse<T>()
        lvResponse.invoke(r)
        tryCatch(iResponse, r)
    }

    suspend fun launch(): T? {
        return tryCatch(iResponse).t
    }

    private suspend fun tryCatch(
        block: suspend () -> IResponse<T>
    ): LvResponse<T> {
        return tryCatch(block, LvResponse())
    }

    private suspend fun <T> tryCatch(
        block: suspend () -> IResponse<T>,
        lvResponse: LvResponse<T>
    ): LvResponse<T> {
        var requestResult: IResponse<T>? = null
        try {
            lvResponse.dispatchStateEvent(ResultState.BeginState())
            requestResult = block.invoke()
            lvResponse.t = requestResult.data()
            (requestResult as? IResponse<T>)?.let {
                // Code 验证
                if (!LvHttp.getCode().contains(it.code())) {
                    lvResponse.dispatchStateEvent(
                        ResultState.ErrorState(
                            requestResult.data(),
                            CodeException(it.code(), it.message())
                        )
                    )
                    // Code 异常处理
                    LvHttp.getErrorDispose(ErrorKey.ErrorCode)?.error?.run {
                        this.invoke(CodeException(it.code(), it.message()))
                    }
                } else {
                    lvResponse.dispatchStateEvent(
                        ResultState.SuccessState(it.data(), it.code(), it.message())
                    )
                }
            } ?: run {
                lvResponse.dispatchStateEvent(
                    ResultState.SuccessState(
                        requestResult.data(), 0, "IResponse is not implemented"
                    )
                )
            }
        } catch (e: Exception) {
            lvResponse.dispatchStateEvent(ResultState.ErrorState(null, e))
            // 自动匹配异常
            ErrorKey.entries.forEach {
                if (it.name == e::class.java.simpleName) {
                    LvHttp.getErrorDispose(it)?.error?.let { it(e) }
                }
            }
            LvHttp.getErrorDispose(ErrorKey.AllException)?.error?.invoke(e)
        } finally {
            // 无论成功还是失败都会执行
            lvResponse.dispatchStateEvent(ResultState.EndState())
        }
        return lvResponse
    }
}