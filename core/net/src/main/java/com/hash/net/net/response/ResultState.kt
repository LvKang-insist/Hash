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
    class BeginState<T> : ResultState<T>()
    class SuccessState<T>(val data: T?, val code: Int, val msg: String) : ResultState<T>()

    /** 有些 error 可能也有 data */
    class ErrorState<T>(val data: T?, val e: Exception) : ResultState<T>()
    class EndState<T> : ResultState<T>()
}

