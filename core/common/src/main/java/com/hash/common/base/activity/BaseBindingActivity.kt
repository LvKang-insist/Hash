package com.hash.common.base.activity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @name BaseBindingActivity
 * @package com.hash.common.base.activity
 * @author 345 QQ:1831712732
 * @time 2024/11/26 23:13
 * @description
 */
abstract class BaseBindingActivity<VB : ViewDataBinding>() : BaseAppActivity() {

    lateinit var binding: VB
    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, layoutId())
        getBrMap().forEach {
            binding.setVariable(it.key, it.value)
        }
    }

}