package com.hash.common.ext

import java.util.Collections

/**
 * @name ListExt
 * @package com.hash.common.ext
 * @author 345 QQ:1831712732
 * @time 2024/12/19 22:18
 * @description
 */

/**
 * 交换位置
 */
fun List<*>.swap(fromPosition: Int, toPosition: Int) {
    Collections.swap(this, fromPosition, toPosition)
}

/**
 * 是否为 null 或 无数据
 */
fun <T : Collection<*>> T?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/**
 * 是否不为 null 且 有数据
 */
fun <T : Collection<*>> T?.isNotNullAndEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

/**
 * 若为空 返回空集合
 */
fun <T> ArrayList<T>?.orEmpty(): ArrayList<T> {
    return this ?: arrayListOf()
}

fun <T> List<T>.toArrayList(): ArrayList<T> {
    return arrayListOf<T>().apply {
        addAll(this@toArrayList)
    }
}