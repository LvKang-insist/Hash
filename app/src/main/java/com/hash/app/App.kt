package com.hash.app

import android.util.Log
import com.hash.common.IApp
import timber.log.Timber

/**
 * @name App
 * @package com.hash.app
 * @author 345 QQ:1831712732
 * @time 2024/11/26 00:04
 * @description
 */
class App : IApp() {

    override fun onCreate() {
        super.onCreate()
        Timber.tag("---345--->").e("App onCreate")
    }

}