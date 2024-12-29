package com.hash.mine

import com.alibaba.android.arouter.facade.annotation.Route
import com.hash.common.base.fragment.BaseBindingFragment
import com.hash.mine.databinding.FragmentMineBinding
import com.hash.router.RouterFragmentPath

/**
 * @name MineFragment
 * @package com.hash.mine
 * @author 345 QQ:1831712732
 * @time 2024/12/15 20:27
 * @description
 */
@Route(path = RouterFragmentPath.Mine.MINE)
class MineFragment : BaseBindingFragment<FragmentMineBinding>() {
    override fun layoutId(): Int  = R.layout.fragment_mine

    override fun initView() {

    }
}