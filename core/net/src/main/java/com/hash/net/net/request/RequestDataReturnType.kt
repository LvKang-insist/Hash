package com.hash.net.net.request

import com.hash.net.net.response.IResponse

/** 请求的数据返回类型 */
enum class RequestDataReturnType {
    /** 任意类型，未实现 IResponse  */
    Object,

    /** 实现了 IResponse 接口 */
    IResponse,
}

abstract class RequestBlock<T>(
    val returnType: RequestDataReturnType
)

class RequestBlockObject<T>(val block: suspend () -> T) :
    RequestBlock<T>(RequestDataReturnType.Object) {

    suspend fun invoke(): T {
        return block.invoke();
    }
}

class RequestBlockIResponse<T>(val block: suspend () -> IResponse<T>) :
    RequestBlock<T>(RequestDataReturnType.IResponse) {

    suspend fun invoke(): IResponse<T> {
        return block.invoke();
    }
}