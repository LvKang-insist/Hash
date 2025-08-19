package com.hash.net.net.response

import com.hash.net.net.error.CodeException

/**
 * @name ResultState
 * @package com.hash.net.net.response
 * @author 345 QQ:1831712732
 * @time 2021/01/05 10:19
 * @description
 */

sealed class ResultState<T> {

    /** 请求成功 */
    class SuccessState<T>(val data: T?, val code: Int, val msg: String) : ResultState<T>()

    /** 请求失败 */
    class ErrorState<T>(val error: Exception) : ResultState<T>()


    /** 获取请求成功后的数据 */
    fun toData(data: (T?) -> Unit): ResultState<T> {
        if (this is SuccessState) data.invoke(this.data)
        return this
    }

    /** 获取请求失败后的错误信息 */
    fun toError(error: (Exception) -> Unit): ResultState<T> {
        if (this is ErrorState) error.invoke(this.error)
        return this
    }
}

