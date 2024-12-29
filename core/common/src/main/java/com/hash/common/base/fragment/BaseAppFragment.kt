package com.hash.common.base.fragment

import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.hash.common.R
import com.hash.common.base.activity.BaseActivity

/**
 * @name BaseAppFragment
 * @package com.hash.common.base.fragment
 * @author 345 QQ:1831712732
 * @time 2024/12/14 00:21
 * @description
 */
abstract class BaseAppFragment : BaseFragment() {


    override fun onResume() {
        initStatusBar()
        super.onResume()
    }


    private fun initStatusBar() {
        if (isStatusBarEnable()) {
            ImmersionBar
                .with(this)
                .statusBarColor(statusBarColor())
                .fitsSystemWindows(fitsSystemWindows())
                .statusBarDarkFont(isStatusDarkFont())
                .init()
            getTitleBar()?.let {
                ImmersionBar.setTitleBar(this, it)
            } ?: kotlin.run {
                view?.findViewById<View>(R.id.toolbar)?.let {
                    ImmersionBar.setTitleBar(this, it)
                }
            }
        }
    }

    /** 是否启用沉浸式状态栏 */
    open fun isStatusBarEnable(): Boolean = true

    /** 状态栏字体深色模式 */
    open fun isStatusDarkFont(): Boolean = true

    open fun getTitleBar(): View? = null

    open fun fitsSystemWindows(): Boolean = false

    /** 状态栏颜色 */
    open fun statusBarColor(): Int = R.color.transparent
}