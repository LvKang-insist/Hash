package com.hash.common.ext

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Formatter
import java.util.Locale

/**
 * @name TimeExt
 * @package com.hash.common.ext
 * @author 345 QQ:1831712732
 * @time 2024/12/19 22:19
 * @description
 */
const val PATTERN_TO_DAY = "yyyy-MM-dd"

const val PATTERN_TO_DAY_1 = "yyyy.MM.dd"

const val PATTERN_TO_YEAR = "yyyy"

const val PATTERN_TO_TIME = "yyyy_MM_dd_HH_mm_ss"
const val PATTERN_TO_MM = "yyyy-MM-dd HH:mm"
const val PATTERN_TO_MM_DD = "MM/dd"

const val PATTERN_TO_TIME_1 = "yyyy-MM-dd HH:mm:ss"

const val PATTERN_TO_TIME_2 = "HH:mm:ss"

const val PATTERN_TO_TIME_3 = "HH:mm"


/**
 * 把時間戳轉  格式化
 */
fun Long.dateFormat(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.US)
    return sdf.format(Date(this))
}

/**
 * 把時間戳轉  格式化
 */
fun Long.dateFormatNoUS(pattern: String): String {
    val sdf = SimpleDateFormat(pattern)
    return sdf.format(Date(this))
}



fun getCurrentTimeMillis(): Long {
    return System.currentTimeMillis()
}


/**
 * 格式化
 */
fun Long.formatDate(): String {
    if (this == 0L) return ""
    val simpleDateFormat = SimpleDateFormat(PATTERN_TO_DAY, Locale.US)
    return simpleDateFormat.format(Date(this))
}

/** 毫秒转秒 */
fun Long.millisToSecond(): Long {
    return this / 1000
}

/** 毫秒转分钟 */
fun Long.millisToMinutes(): Long {
    return this / 1000 / 60
}

/** 时间戳转小时 */
fun Long.toHours(): Int {
    val date = Date(this)
    val calendar = Calendar.getInstance().apply { time = date }
    return calendar.get(Calendar.HOUR_OF_DAY)
}

/** 将毫秒格式化为固定的格式 */
fun Long.millisToForTime(): String {
    val totalSeconds = this / 1000
    return totalSeconds.toFormatTime()
}

/** 将秒格式化为固定的格式 */
fun Long.toFormatTime(): String {
    val seconds = this % 60
    val minutes = this / 60 % 60
    val hours = this / 3600
    return if (hours > 0L) {
        Formatter().format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds).toString()
    } else {
        Formatter().format(Locale.US, "%02d:%02d", minutes, seconds).toString()
    }
}

/**
 * 傳入時間是否和現在是同一天
 */
fun Long.isToday(): Boolean {
    return this.isThisTime(PATTERN_TO_DAY)
}

/**
 * 傳入時間是否和現在是同一年
 */
fun Long.isToYear(): Boolean {
    return this.isThisTime(PATTERN_TO_YEAR)
}

/**
 * 根據時間用戶相同的格式化後對比是否是一樣來判斷  是否在同一天or 同一月
 */
fun Long.isThisTime(pattern: String): Boolean {
    val parameter = this.dateFormat(pattern)
    val now = getCurrentTimeMillis().dateFormat(pattern)
    return parameter == now
}

/**
 * 格式化  根据与当前时间的不同返回不同的格式
 * 1 如果是今天  返回 HH:mm
 * 2 如果是同一年  返回 MM-dd HH:mm
 * 3 如果不是同一年  返回 yyyy-MM-dd HH:mm
 */
fun Long.toFormatDate(): String {
    val pattern = if (this.isToday()) {
        "HH:mm"
    } else if (this.isToYear()) {
        "MM-dd HH:mm"
    } else {
        "yyyy-MM-dd HH:mm"
    }
    return this.dateFormat(pattern)
}

/**
 * 根据时间戳返回年龄
 */
fun Long.getAge(): String {
    val currentDate = Calendar.getInstance()
    val birthDate = Calendar.getInstance().apply {
        timeInMillis = this@getAge
    }

    var age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)

    if (currentDate.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
        age--
    }
    return age.toString()
}


/**
 * 获取倒计时
 */
fun Long.toFormatTiming(): String {
    val m = this / 60 % 60
    val s = this % 60
    val mm = if (m == 0L) {
        "00"
    } else if (m <= 9) {
        "0$m"
    } else {
        m.toString()
    }
    val ss = if (s == 0L) {
        "00"
    } else if (s <= 9) {
        "0$s"
    } else {
        s.toString()
    }
    return "$mm:$ss"
}
