package com.hash.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.hash.common.base.activity.BaseBindingActivity
import com.hash.login.databinding.ActivityLoginBinding
import com.hash.router.RouterActivityPath

@Route(path = RouterActivityPath.Login.LOGIN)
class LoginActivity : BaseBindingActivity<ActivityLoginBinding>() {

    override fun layoutId(): Int = R.layout.activity_login

    override fun initView() {

    }
}