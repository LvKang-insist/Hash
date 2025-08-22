package com.hash.net.net.response

import com.hash.net.net.request.LvRequest
import com.hash.net.net.request.RequestBlock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

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

}


class ResultStateFlow<T>(val block: RequestBlock<T>) {
    private var begin: (() -> Unit)? = null
    private var end: (() -> Unit)? = null
    private var data: ((T) -> Unit)? = null
    private var dataAndCode: ((T, Int) -> Unit)? = null

    private var nullData: (() -> Unit)? = null
    private var dataAndCodeNull: (() -> Unit)? = null

    private var error: ((Exception) -> Unit)? = null


    fun toBegin(block: () -> Unit): ResultStateFlow<T> {
        this.begin = block
        return this
    }

    fun toData(block: (T) -> Unit): ResultStateFlow<T> {
        data = block
        return this
    }

    fun toData(block: (T, Int) -> Unit): ResultStateFlow<T> {
        dataAndCode = block
        return this
    }

    fun onNullData(block: () -> Unit): ResultStateFlow<T> {
        nullData = block
        return this
    }

    fun toDataAndCodeNull(block: () -> Unit): ResultStateFlow<T> {
        dataAndCodeNull = block
        return this
    }

    fun toError(block: (Exception) -> Unit): ResultStateFlow<T> {
        error = block
        return this
    }

    fun toEnd(block: () -> Unit): ResultStateFlow<T> {
        this.end = block
        return this
    }

    fun async(scope: CoroutineScope = CoroutineScope(Dispatchers.Main)) {
        scope.launch {
            dispatchBegin()
            val state = LvRequest().request(block) // 发起请求
            when (state) {
                is ResultState.SuccessState -> {
                    dispatchData(state.data, state.code)
                }
                is ResultState.ErrorState -> {
                    dispatchError(state.error)
                }
            }
            dispatchEnd()
        }
        return
    }

    suspend fun sync(): T? {
        val state = LvRequest().request(block)
        return when (state) {
            is ResultState.SuccessState -> state.data
            is ResultState.ErrorState -> null
        }
    }

    private fun dispatchBegin() {
        this.begin?.invoke()
    }

    private fun dispatchEnd() {
        this.end?.invoke()
    }

    private fun dispatchData(data: T?, code: Int) {
        if (data == null) {
            this.nullData?.invoke()
            this.dataAndCodeNull?.invoke()
            return
        } else {
            this.data?.invoke(data)
            this.dataAndCode?.invoke(data, code)
        }
    }

    private fun dispatchError(e: Exception) {
        this.error?.invoke(e)
    }
}
