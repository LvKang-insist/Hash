package com.hash.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.hash.router.RouterFragmentPath

/**
 * @name MainPageAdapter
 * @package com.hash.main.adapter
 * @author 345 QQ:1831712732
 * @time 2024/12/15 22:48
 * @description
 */
class MainPageAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val list: List<Any>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ARouter.getInstance().build(RouterFragmentPath.Home.HOME).navigation() as Fragment
            1 -> return ARouter.getInstance().build(RouterFragmentPath.Discover.DISCOVER).navigation() as Fragment
            2 -> return ARouter.getInstance().build(RouterFragmentPath.Msg.MSG).navigation() as Fragment
            3 -> return ARouter.getInstance().build(RouterFragmentPath.Mine.MINE).navigation() as Fragment
        }
        return Fragment()
    }
}