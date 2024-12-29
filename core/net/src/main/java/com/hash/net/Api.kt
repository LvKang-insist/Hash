package com.hash.net

import com.hash.net.api.KyApiService
import com.hash.net.net.LvHttp

/**
 * @name Api
 * @package com.hash.net
 * @author 345 QQ:1831712732
 * @time 2024/12/21 22:49
 * @description
 */


val kyApi by lazy { LvHttp.createApi(KyApiService::class.java) }