package com.hash.common.ext

import android.util.Log
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

/**
 * @name GsonExt
 * @package com.hash.common.ext
 * @author 345 QQ:1831712732
 * @time 2024/12/19 22:17
 * @description
 */

private val gson by lazy { //使用这个创建Gson是为了解决  = 被转换成\u003d的问题  直接new 会出现这个问题
    GsonBuilder()
        .disableHtmlEscaping()
        .serializeSpecialFloatingPointValues()
        .create() }


fun <T:Any> String?.toAny(any: Class<T>):T?{
    try {
        return if (this.isNullOrEmpty()) {
            null
        } else {
            gson.fromJson(this, any)
        }
    } catch (e: Exception) {
        Log.e("GSON",e.message.toString())
    }
    return null
}

fun <T:Any> String?.toAny(any: Type):T?{
    try {
        return if (this == null) {
            null
        } else {
            gson.fromJson(this, any)
        }
    } catch (e: Exception) {
        Log.e("GSON",e.message.toString())
    }
    return null
}

fun Any.toJson():String{
    return gson.toJson(this)
}