package com.hash.common.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hash.common.base.activity.BaseActivity

/**
 * @name BaseBindingFragment
 * @package com.hash.common.base.fragment
 * @author 345 QQ:1831712732
 * @time 2024/12/14 00:22
 * @description
 */
abstract class BaseBindingFragment<T : ViewDataBinding> : BaseAppFragment() {

    lateinit var binding: T

    override fun initContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        getBrMap().forEach {
            binding.setVariable(it.key, it.value)
        }
        return binding.root
    }

}