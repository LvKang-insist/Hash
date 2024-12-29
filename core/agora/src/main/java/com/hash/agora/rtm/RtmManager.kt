package com.hash.agora.rtm

import io.agora.rtm.RtmClient
import io.agora.rtm.RtmConfig
import io.agora.rtm.RtmConstants.RtmLogLevel
import io.agora.rtm.RtmLogConfig
import timber.log.Timber

/**
 * @name RtmManager
 * @package com.hash.agora.rtm
 * @author 345 QQ:1831712732
 * @time 2024/12/29 22:00
 * @description
 */
object RtmManager {

    private lateinit var mRtmClient: RtmClient

    private const val TAG = "RtmManager:"

    private val loginResultCallback = LoginResultCallback()

    fun initSdk() {
        mRtmClient = RtmClient.create(rtmConfig())
        loginRtm()
    }

    /** 刷新token */
    fun refreshToken(isReLogin: Boolean) {
        //掉接口获取token
        if (isReLogin) {
            loginRtm()
        } else {
            mRtmClient.renewToken("", null)
        }
    }

    /** 登录 rtm */
    fun loginRtm() {
        mRtmClient.login("", loginResultCallback)
    }

    /** 退出登录 rtm */
    fun logoutRtm() {
        if (::mRtmClient.isInitialized) {
            mRtmClient.logout(object : io.agora.rtm.ResultCallback<Void> {
                override fun onSuccess(p0: Void?) {
                    Timber.d("$TAG logoutRtm onSuccess")
                }

                override fun onFailure(p0: io.agora.rtm.ErrorInfo?) {
                    Timber.d("$TAG logoutRtm onFailure  ${p0?.errorCode} ${p0?.errorCode}")
                }
            })
        }
    }


    private fun rtmConfig(): RtmConfig = RtmConfig.Builder("", "")
        .logConfig(rtmLogConfig())
        .build()

    private fun rtmLogConfig(): RtmLogConfig = RtmLogConfig().apply {
        filePath = "./logfile/"
        fileSize = 1024
        setLevel(RtmLogLevel.INFO)
    }

}