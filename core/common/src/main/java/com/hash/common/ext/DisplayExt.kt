package com.hash.common.ext

import com.hash.common.IApp

/**
 * @name displayExt
 * @package com.hash.common.ext
 * @author 345 QQ:1831712732
 * @time 2024/12/19 22:16
 * @description
 */

/** dp值转换为px值 */
fun Float.dp2px():Int{
    return  (IApp.instant.resources.displayMetrics.density * this + 0.5f).toInt()
}

fun Float.px2dp():Int{
    return  (this / IApp.instant.resources.displayMetrics.density + 0.5f).toInt()
}

/** 获取屏幕宽值 */
val screenWidth
    get() = IApp.instant.resources.displayMetrics.widthPixels

/** 获取屏幕高值 */
val screenHeight
    get() = IApp.instant.resources.displayMetrics.heightPixels


/** 获取屏幕像素：对获取的宽高进行拼接。例：1080X2340。 */
fun screenPixel(): String {
    IApp.instant.resources.displayMetrics.run {
        return "${widthPixels}X${heightPixels}"
    }
}