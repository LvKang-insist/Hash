package com.hash.bean.home

/**
 * @name HomeDataBean
 * @package com.hash.bean.home
 * @author 345 QQ:1831712732
 * @time 2025/01/03 23:43
 * @description
 */
data class HomeDataBean(
    val type: Int,
    val homeListItem: HomeListBean.HomeListItem? = null,
    val banner: BannerListBean? = null,
)