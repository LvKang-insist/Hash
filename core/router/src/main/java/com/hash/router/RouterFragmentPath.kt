package com.hash.router

/**
 * @name RouterFragmentPath
 * @package com.hash.router
 * @author 345 QQ:1831712732
 * @time 2024/12/15 20:06
 * @description
 */
class RouterFragmentPath {

    object Home {
        private const val ROOT = "/home"
        /** 首页 */
        const val HOME = "${ROOT}/home"
    }

    object Discover {
        private const val ROOT = "/discover"

        /** 发现 */
        const val DISCOVER = "${ROOT}/discover"
    }


    object Msg {
        private const val ROOT = "/message"
        /** 消息 */
        const val MSG = "${ROOT}/message"
    }

    object Mine {
        private const val ROOT = "/mine"

        /** 我的 */
        const val MINE = "${ROOT}/mine"
    }

}