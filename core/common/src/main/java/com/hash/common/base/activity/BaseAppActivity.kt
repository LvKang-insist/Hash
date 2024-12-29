package com.hash.common.base.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import com.gyf.immersionbar.ImmersionBar.setTitleBar
import com.gyf.immersionbar.ktx.immersionBar
import com.hash.common.R
import com.hash.common.base.dialog.BaseDialog
import com.hash.common.base.dialog.WaitDialog

/**
 * @name BaseAppActivity
 * @package com.hash.common.base.activity
 * @author 345 QQ:1831712732
 * @time 2024/11/26 22:56
 * @description
 */
abstract class BaseAppActivity : BaseActivity() {

    private var dialog: BaseDialog? = null
    private var dialogCount: Int = 0

    open fun isShowDialog(): Boolean = dialog != null && dialog!!.isShowing

    override fun showLoading() {
        if (isFinishing || isDestroyed) {
            return
        }
        dialogCount++
        postDelayed(Runnable {
            if ((dialogCount <= 0) || isFinishing || isDestroyed) {
                return@Runnable
            }
            if (dialog == null) {
                dialog = WaitDialog.Builder(this)
                    .setCancelable(false)
                    .create()
            }
            if (!dialog!!.isShowing) {
                dialog!!.show()
            }
        }, 300)
    }

    /**
     * 隐藏加载对话框
     */
    override fun dismissLoading() {
        if (isFinishing || isDestroyed) {
            return
        }
        if (dialogCount > 0) {
            dialogCount--
        }
        if ((dialogCount != 0) || (dialog == null) || !dialog!!.isShowing) {
            return
        }
        dialog?.dismiss()
    }

    override fun initLayout() {
        super.initLayout()
        initStatusBar()
        setBack()
    }

    private fun setBack() {
        findViewById<View>(R.id.back)?.setOnClickListener {
            finish()
        }
    }

    private fun initStatusBar() {
        if (isStatusBarEnable()) {
            immersionBar {
                statusBarColor(R.color.transparent)
                statusBarDarkFont(isStatusDarkFont())
                getTitleBar()?.let {
                    setTitleBar(this@BaseAppActivity, it)
                } ?: kotlin.run {
                    findViewById<View>(R.id.toolbar)?.let {
                        setTitleBar(this@BaseAppActivity, it)
                    }
                }
            }
        }
    }

    /** 是否启用沉浸式状态栏 */
    open fun isStatusBarEnable(): Boolean = true

    /** 状态栏字体深色模式 */
    open fun isStatusDarkFont(): Boolean = true

    open fun getTitleBar(): View? = null

    override fun onDestroy() {
        super.onDestroy()
        getContentView()?.run {
            traverse(this)
        }
        if (isShowDialog()) {
            dismissLoading()
        }
        dialog = null
    }

    private fun traverse(root: ViewGroup) {
        val childCount = root.childCount
        for (i in 0 until childCount) {
            val child = root.getChildAt(i)
            if (child is ViewGroup) {
                child.background = null
                traverse(child)
            } else {
                EditText(this).addTextChangedListener { }
                child.background = null
                when {
                    child is ImageView -> child.setImageDrawable(null)
                }
            }
        }
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
        overridePendingTransition(R.anim.right_in_activity, R.anim.right_out_activity)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.left_in_activity, R.anim.left_out_activity)
    }
}