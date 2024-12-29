package com.hash.common.base.fragment

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.hash.common.base.InitAction
import com.hash.common.base.action.BundleAction
import com.hash.common.base.action.HandlerAction
import com.hash.common.base.action.KeyboardAction
import com.hash.common.base.activity.BaseActivity

/**
 * @name BaseFragment
 * @package com.hash.common.base.fragment
 * @author 345 QQ:1831712732
 * @time 2024/12/13 23:41
 * @description
 */
abstract class BaseFragment : Fragment(), InitAction, BundleAction,
    KeyboardAction, HandlerAction {

    private var isLazyLoad = false

    var activity: BaseActivity? = null

    private var rootView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activity = requireActivity() as? BaseActivity
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = initContentView(inflater, container)
        initParam()
        initView()
        listener()
        observer()
        return rootView
    }

    open fun initContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return layoutId().run { inflater.inflate(this, container, false) }
    }

    override fun onResume() {
        super.onResume()
        onFragmentResume(!isLazyLoad)
        if (!isLazyLoad) {
            isLazyLoad = true
            loadData()
        }
    }

    override fun getBundle(): Bundle? = arguments

    /**
     * Fragment 可见回调
     *
     * @param first                 是否首次调用
     */
    protected open fun onFragmentResume(first: Boolean) {}

    /**
     * Activity 可见回调
     */
    protected open fun onActivityResume() {}

    override fun onDestroyView() {
        super.onDestroyView()
        rootView = null
    }

    override fun onDestroy() {
        super.onDestroy()
        isLazyLoad = false
        removeCallbacks()
    }

    override fun onDetach() {
        super.onDetach()
        activity = null
    }

    override fun getView(): View? = rootView


    /** 获取绑定的 Activity */
    @Suppress("UNCHECKED_CAST")
    open fun <T : Activity> getAttachActivity(): T? = activity as T

    open fun getApplication(): Application? = activity?.application


    open fun startActivity(clazz: Class<out Activity>) =
        startActivity(Intent(context, clazz))


    open fun startActivity(clazz: Class<out Activity>, bundle: Bundle) =
        startActivity(Intent(context, clazz).apply { putExtras(bundle) })


    /**
     * startActivityForResult 方法优化
     */
    open fun startActivityForResult(
        clazz: Class<out Activity>, callback: BaseActivity.OnActivityCallback?
    ) {
        activity?.startActivityForResult(clazz, callback)
    }

    open fun startActivityForResult(intent: Intent, callback: BaseActivity.OnActivityCallback?) {
        activity?.startActivityForResult(intent, null, callback)
    }

    open fun startActivityForResult(
        intent: Intent, options: Bundle?, callback: BaseActivity.OnActivityCallback?
    ) {
        activity?.startActivityForResult(intent, options, callback)
    }


    open fun finishActivity() {
        activity?.let {
            if (it.isFinishing || it.isDestroyed) return
            it.finish()
        }
    }


    /**
     * Fragment 按键事件派发
     */
    open fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        val fragments: MutableList<Fragment?> = childFragmentManager.fragments
        for (fragment: Fragment? in fragments) {
            // 这个子 Fragment 必须是 BaseFragment 的子类，并且处于可见状态
            if (fragment !is BaseFragment || fragment.lifecycle.currentState != Lifecycle.State.RESUMED) {
                continue
            }
            // 将按键事件派发给子 Fragment 进行处理
            if (fragment.dispatchKeyEvent(event)) {
                // 如果子 Fragment 拦截了这个事件，那么就不交给父 Fragment 处理
                return true
            }
        }
        return when (event?.action) {
            KeyEvent.ACTION_DOWN -> onKeyDown(event.keyCode, event)
            KeyEvent.ACTION_UP -> onKeyUp(event.keyCode, event)
            else -> false
        }
    }

    /** 按键按下事件回调，默认不拦截 */
    open fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean = false

    /** 按键抬起事件回调，默认不拦截 */
    open fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        // 默认不拦截按键事件
        return false
    }

    override fun getContext(): Context? = activity
}