package com.hash.common.core.indicator

import androidx.viewpager2.widget.ViewPager2
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * @name ViewPager2Helper
 * @package com.hash.common.other
 * @author 345 QQ:1831712732
 * @time 2024/12/21 00:03
 * @description
 */

object ViewPager2Helper {
    fun bind(indicator: MagicIndicator, viewPager2: ViewPager2) {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) = indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)

            override fun onPageSelected(position: Int) = indicator.onPageSelected(position)

            override fun onPageScrollStateChanged(state: Int) =
                indicator.onPageScrollStateChanged(state)
        })
    }
}