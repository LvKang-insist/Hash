package com.hash.agora.rtm

import io.agora.rtm.ErrorInfo
import io.agora.rtm.ResultCallback
import io.agora.rtm.RtmConstants.RtmErrorCode
import timber.log.Timber

/**
 * @name LoginResultCallback
 * @package com.hash.agora.rtm
 * @author 345 QQ:1831712732
 * @time 2024/12/29 22:49
 * @description
 */
class LoginResultCallback : ResultCallback<Void> {

    private var loginCount = 0

    private val tag = "Agora RTM LoginResultCallback："

    override fun onSuccess(responseInfo: Void?) {
        loginCount = 0
        Timber.d("$tag login onSuccess")
    }

    override fun onFailure(errorInfo: ErrorInfo?) {
        val code = errorInfo?.errorCode ?: 0
        if (code == RtmErrorCode.INVALID_TOKEN ||
            code == RtmErrorCode.TOKEN_EXPIRED ||
            code == RtmErrorCode.LOGIN_NOT_AUTHORIZED
        ) {
            //Token 无效。  : token 已过期，登录被拒绝。 Token 验证失败。
            RtmManager.refreshToken(true)
        } else {
            if (loginCount > 6) {
                //重试 6次后还是没有连接上，提示切换网络
                Timber.d("$tag retry $loginCount loginRtm , ${errorInfo?.errorCode} ${errorInfo?.errorReason} ")
            } else {
                loginCount++
                RtmManager.loginRtm()
            }
        }

        Timber.d("$tag onFailure: ${errorInfo?.errorCode} ${errorInfo?.errorReason} ")
    }
}