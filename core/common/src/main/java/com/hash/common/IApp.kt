package com.hash.common

import android.app.Application
import android.content.pm.ApplicationInfo
import android.util.Log
import com.hash.common.config.GlideApp
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * @name IApp
 * @package com.hash.main
 * @author 345 QQ:1831712732
 * @time 2024/11/26 00:03
 * @description
 */
open class IApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instant = this
        //是否是debug模式， 设置 debuggable 后无效
        isDebug = applicationInfo != null &&
                (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0

        initSdk()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // 清理所有图片内存缓存
        GlideApp.get(this).onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // 根据手机内存剩余情况清理图片内存缓存
        GlideApp.get(this).onTrimMemory(level)
    }


    companion object {
        var instant: Application by Delegates.notNull()
        var isDebug: Boolean by Delegates.notNull()

        fun initSdk() {
            initTimer()
        }

        private fun initTimer() {
            if (isDebug) {
                Timber.plant(Timber.DebugTree());
            } else {
                Timber.plant(object : Timber.Tree() {
                    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                        // 上传到服务器
                    }
                });
            }
        }
    }

}