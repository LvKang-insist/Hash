package com.hash.net.response

import com.hash.net.net.response.IResponse

/**
 * @name WanResponse
 * @package com.hash.net.response
 * @author 345 QQ:1831712732
 * @time 2024/12/31 23:55
 * @description
 */
data class WanResponse<T>(val data: T, val errorCode: Int, val errorMsg: String) :
    IResponse<T> {
    override fun data(): T = data

    override fun code(): Int = if (errorCode == 0) 200 else errorCode

    override fun message(): String = errorMsg
}