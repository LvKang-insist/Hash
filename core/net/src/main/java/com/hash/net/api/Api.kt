package com.hash.net.api

import com.hash.net.net.LvHttp

/**
 * @name Api
 * @package com.hash.net
 * @author 345 QQ:1831712732
 * @time 2024/12/21 22:49
 * @description
 */


val api by lazy { LvHttp.createApi(WanAndroidApi::class.java) }