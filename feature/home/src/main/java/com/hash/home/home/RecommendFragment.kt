package com.hash.home.home

import androidx.fragment.app.viewModels
import com.hash.common.base.fragment.BaseBindingFragment
import com.hash.home.R
import com.hash.home.databinding.FragmentRecommendBinding
import com.hash.home.home.viewmodel.RecommendViewModel

/**
 * @name RecommendFragment
 * @package com.hash.home
 * @author 345 QQ:1831712732
 * @time 2024/12/21 00:12
 * @description
 */
class RecommendFragment : BaseBindingFragment<FragmentRecommendBinding>() {

    private val viewModel by viewModels<RecommendViewModel>()

    override fun layoutId(): Int = R.layout.fragment_recommend

    override fun initView() {

    }

    override fun listener() {
        binding.button.setOnClickListener {
            viewModel.recommendList()
        }
        binding.button1.setOnClickListener {
            viewModel.wan()
        }
    }
}