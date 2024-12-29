package com.hash.main.splash

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.hash.common.base.activity.BaseBindingActivity
import com.hash.main.R
import com.hash.main.databinding.ActivitySplashBinding
import com.hash.router.RouterActivityPath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @name SplashActivity
 * @package com.hash.main.splash
 * @author 345 QQ:1831712732
 * @time 2024/12/17 23:16
 * @description
 */
class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {

    override fun layoutId(): Int = R.layout.activity_splash

    private val splashDuration = 3 * 1000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f,
            1.05f,
            1f,
            1.05f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun initView() {
        binding.logo.startAnimation(scaleAnimation)
        binding.root.startAnimation(alphaAnimation)
        binding.logo.setOnClickListener {

//            overridePendingTransition(R.anim.activity_from_bottom_to_top_in, R.anim.activity_from_bottom_to_top_out)
//            finish()
        }
        lifecycleScope.launch {
//            delay(splashDuration)
            launch(Dispatchers.Main) {
                ARouter.getInstance().build(RouterActivityPath.Main.MAIN)
                    .withTransition(
//                        com.hash.common.R.anim.activity_alpha_in,
//                        com.hash.common.R.anim.activity_alpha_out
                        com.hash.common.R.anim.activity_from_bottom_to_top_in,
                        com.hash.common.R.anim.activity_from_bottom_to_top_out
                    )
                    .navigation(this@SplashActivity)
                delay(500)
                finish()
            }
        }

    }


}