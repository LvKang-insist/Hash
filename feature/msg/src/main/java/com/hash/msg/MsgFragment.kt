package com.hash.msg

import com.alibaba.android.arouter.facade.annotation.Route
import com.hash.common.base.fragment.BaseBindingFragment
import com.hash.msg.databinding.FragmentMsgBinding
import com.hash.router.RouterFragmentPath

/**
 * @name MsgFragment
 * @package com.hash.msg
 * @author 345 QQ:1831712732
 * @time 2024/12/15 20:28
 * @description
 */
@Route(path = RouterFragmentPath.Msg.MSG)
class MsgFragment : BaseBindingFragment<FragmentMsgBinding>() {
    override fun layoutId(): Int  = R.layout.fragment_msg

    override fun initView() {

    }
}