package com.hash.discover

import com.alibaba.android.arouter.facade.annotation.Route
import com.hash.common.base.fragment.BaseBindingFragment
import com.hash.discover.databinding.FragmentDiscoverBinding
import com.hash.router.RouterFragmentPath

/**
 * @name Discover
 * @package com.hash.discover
 * @author 345 QQ:1831712732
 * @time 2024/12/15 20:25
 * @description
 */
@Route(path = RouterFragmentPath.Discover.DISCOVER)
class DiscoverFragment : BaseBindingFragment<FragmentDiscoverBinding>() {
    override fun layoutId(): Int  = R.layout.fragment_discover

    override fun initView() {

    }
}