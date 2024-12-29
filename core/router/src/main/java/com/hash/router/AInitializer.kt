package com.hash.router

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.startup.Initializer
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @name Initializer
 * @package com.hash.router
 * @author 345 QQ:1831712732
 * @time 2024/12/15 19:52
 * @description
 */
class AInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(context as Application); // 尽可能早，推荐在Application中初始化
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}