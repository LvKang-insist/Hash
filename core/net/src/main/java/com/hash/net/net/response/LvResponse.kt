package com.hash.net.net.response

import com.hash.net.net.response.ResultState.*

/**
 * @name Result
 * @package com.hash.net.net.response
 * @author 345 QQ:1831712732
 * @time 2024/12/22 22:44
 * @description 用于封装请求结果的类
 */

class LvResponse<T>(var t: T? = null) {

    private var begin: (() -> Unit)? = null

    /** 成功 */
    private var success: ((T) -> Unit)? = null
    private var successNotData: (() -> Unit)? = null
    private var successCodeMsg: ((T?, Int, String) -> Unit)? = null

    /** 失败 */
    private var error: ((T?, Exception) -> Unit)? = null

    /** 结束 */
    private var finish: (() -> Unit)? = null


    internal fun dispatchStateEvent(state: ResultState<T>) {
        when (state) {
            is BeginState -> begin?.invoke()
            is SuccessState -> {
                if (state.data != null) {
                    success?.invoke(state.data)
                    successCodeMsg?.invoke(state.data, state.code, state.msg)
                } else {
                    successNotData?.invoke()
                    successCodeMsg?.invoke(state.data, state.code, state.msg)
                }
            }

            is ErrorState -> {
                error?.invoke(state.data, state.e)
            }

            is EndState -> finish?.invoke()
        }
    }


    fun toBegin(begin: () -> Unit): LvResponse<T> = apply { this.begin = begin }

    /** 获取数据相关 */
    fun toData(data: (T) -> Unit): LvResponse<T> = apply { success = data }
    fun toNotData(data: () -> Unit): LvResponse<T> = apply { successNotData = data }
    fun toDataCodeMsg(data: (T?, code: Int, msg: String) -> Unit): LvResponse<T> =
        apply { successCodeMsg = data }

    /** 失败相关 */
    fun toError(error: (T?, Exception) -> Unit): LvResponse<T> = apply { this.error = error }

    /** 结束 */
    fun toFinish(finish: () -> Unit): LvResponse<T> = apply { this.finish = finish }

}
