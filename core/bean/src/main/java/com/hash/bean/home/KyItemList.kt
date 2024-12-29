package com.hash.bean.home

/**
 * @name KyResponse
 * @package com.hash.net.response
 * @author 345 QQ:1831712732
 * @time 2024/12/19 23:54
 * @description
 */

data class KyItemList(
    val adExist: Boolean, // false
    val count: Int, // 8
    val itemList: List<Item>,
    val nextPageUrl: String, // http://baobab.kaiyanapp.com/api/v5/index/tab/allRec?page=2&isTag=true&adIndex=3
    val total: Int // 0
) {

    data class Item(
        val adIndex: Int, // -1
        val data: Data,
        val id: Int, // 0
        val tag: Any?, // null
        val trackingData: Any?, // null
        val type: String // textCard
    ) {
        data class Data(
            val actionUrl: String?, // eyepetizer://tag/1032/?title=%E7%BB%99%E4%BD%A0%E8%AE%B2%E4%B8%AA%E5%A5%BD%E6%95%85%E4%BA%8B
            val ad: Boolean?, // false
            val adTrack: List<Any?>?,
            val author: Author?,
            val brandWebsiteInfo: Any?, // null
            val campaign: Any?, // null
            val category: String?, // 剧情
            val collected: Boolean?, // false
            val consumption: Consumption?,
            val content: Content?,
            val cover: Cover?,
            val dataType: String, // TextCard
            val date: Long?, // 1734710483000
            val description: String?, // 一场悲惨事故发生一年后，人形机器人 Robbie 被内疚的创造者 LETICIA 激活，假扮成一名普通青少年。尽管是一台最先进的机器，Robbie 的举止却远非完美无缺。进入高中后，Robbie 很快引起了被排斥的 MAX 和忧郁的 CONNOR 的关注，他必须应对青春期的不确定性，以隐藏一个令人难以忘怀的秘密。 From DUST
            val descriptionEditor: String?, // 一场悲惨事故发生一年后，人形机器人 Robbie 被内疚的创造者 LETICIA 激活，假扮成一名普通青少年。尽管是一台最先进的机器，Robbie 的举止却远非完美无缺。进入高中后，Robbie 很快引起了被排斥的 MAX 和忧郁的 CONNOR 的关注，他必须应对青春期的不确定性，以隐藏一个令人难以忘怀的秘密。 From DUST
            val descriptionPgc: String?,
            val duration: Int?, // 1651
            val favoriteAdTrack: Any?, // null
            val follow: Any?, // null
            val header: Header?,
            val id: Int?, // 0
            val idx: Int?, // 0
            val ifLimitVideo: Boolean?, // false
            val label: Any?, // null
            val labelList: List<Any?>?,
            val lastViewTime: Any?, // null
            val library: String?, // DEFAULT
            val playInfo: List<PlayInfo>?,
            val playUrl: String?, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=326702&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
            val played: Boolean?, // false
            val playlists: Any?, // null
            val promotion: Any?, // null
            val provider: Provider?,
            val reallyCollected: Boolean?, // false
            val recallSource: String?,
            val recall_source: String?,
            val releaseTime: Long?, // 1734710483000
            val remark: String?,
            val resourceType: String?, // video
            val searchWeight: Int?, // 0
            val shareAdTrack: Any?, // null
            val slogan: Any?, // null
            val src: Int?, // 7
            val subTitle: Any?, // null
            val subtitles: List<Any?>?,
            val tags: List<Tag>?,
            val text: String?, // 给你讲个好故事
            val thumbPlayUrl: Any?, // null
            val title: String?, // 隐藏深处的秘密，一个假扮普通高中生的机器人
            val titlePgc: String?, // 剧情
            val type: String?, // header5
            val videoPosterBean: VideoPosterBean?,
            val waterMarks: Any?, // null
            val webAdTrack: Any?, // null
            val webUrl: WebUrl?
        ) {
            data class Author(
                val adTrack: Any?, // null
                val approvedNotReadyVideoCount: Int, // 0
                val description: String, // 我们只做最好的。
                val expert: Boolean, // false
                val follow: Follow,
                val icon: String, // http://ali-img.kaiyanapp.com/50fe7ed6bbb5e5e52620e8664d58d323.png?imageMogr2/quality/60/format/jpg
                val id: Int, // 2369
                val ifPgc: Boolean, // true
                val latestReleaseTime: Long, // 1734710483000
                val link: String,
                val name: String, // 剧情短片精选
                val recSort: Int, // 0
                val shield: Shield,
                val videoNum: Int // 719
            ) {
                data class Follow(
                    val followed: Boolean, // false
                    val itemId: Int, // 2369
                    val itemType: String // author
                )

                data class Shield(
                    val itemId: Int, // 2369
                    val itemType: String, // author
                    val shielded: Boolean // false
                )
            }

            data class Consumption(
                val collectionCount: Int, // 0
                val realCollectionCount: Int, // 0
                val replyCount: Int, // 2
                val shareCount: Int // 0
            )

            data class Content(
                val adIndex: Int, // -1
                val `data`: Data,
                val id: Int, // 0
                val tag: Any?, // null
                val trackingData: Any?, // null
                val type: String // video
            ) {
                data class Data(
                    val ad: Boolean, // false
                    val adTrack: List<Any?>,
                    val author: Author,
                    val brandWebsiteInfo: Any?, // null
                    val campaign: Any?, // null
                    val category: String, // 剧情
                    val collected: Boolean, // false
                    val consumption: Consumption,
                    val cover: Cover,
                    val dataType: String, // VideoBeanForClient
                    val date: Long, // 1734710475000
                    val description: String, // 那是 2028 年，英格兰与世界其他国家隔绝，由极权主义国家的少数权贵统治。跟随三十多岁的阿迪尔领取当月的福利支票，福利国家正在崩溃，由于公共部门削减开支，所有服务现在都已完全自动化。由 Edward Lomas 执导的科幻片「福利」是一部带有尖锐信息的讽刺作品。 From DUST
                    val descriptionEditor: String, // 那是 2028 年，英格兰与世界其他国家隔绝，由极权主义国家的少数权贵统治。跟随三十多岁的阿迪尔领取当月的福利支票，福利国家正在崩溃，由于公共部门削减开支，所有服务现在都已完全自动化。由 Edward Lomas 执导的科幻片「福利」是一部带有尖锐信息的讽刺作品。 From DUST
                    val descriptionPgc: String,
                    val duration: Int, // 226
                    val favoriteAdTrack: Any?, // null
                    val id: Int, // 326500
                    val idx: Int, // 0
                    val ifLimitVideo: Boolean, // false
                    val label: Any?, // null
                    val labelList: List<Any?>,
                    val lastViewTime: Any?, // null
                    val library: String, // DEFAULT
                    val playInfo: List<PlayInfo>,
                    val playUrl: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=326500&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss&udid=
                    val played: Boolean, // false
                    val playlists: Any?, // null
                    val promotion: Any?, // null
                    val provider: Provider,
                    val reallyCollected: Boolean, // false
                    val recallSource: String,
                    val recall_source: String,
                    val releaseTime: Long, // 1734710475000
                    val remark: String,
                    val resourceType: String, // video
                    val searchWeight: Int, // 0
                    val shareAdTrack: Any?, // null
                    val slogan: Any?, // null
                    val src: Int, // 7
                    val subtitles: List<Any?>,
                    val tags: List<Tag>,
                    val thumbPlayUrl: Any?, // null
                    val title: String, // 压抑的社会恐怖，反乌托邦讽刺科幻「福利」
                    val titlePgc: String, // 剧情1
                    val type: String, // NORMAL
                    val videoPosterBean: Any?, // null
                    val waterMarks: Any?, // null
                    val webAdTrack: Any?, // null
                    val webUrl: WebUrl
                ) {
                    data class Author(
                        val adTrack: Any?, // null
                        val approvedNotReadyVideoCount: Int, // 0
                        val description: String, // 我们只做最好的。
                        val expert: Boolean, // false
                        val follow: Follow,
                        val icon: String, // http://ali-img.kaiyanapp.com/50fe7ed6bbb5e5e52620e8664d58d323.png?imageMogr2/quality/60/format/jpg
                        val id: Int, // 2369
                        val ifPgc: Boolean, // true
                        val latestReleaseTime: Long, // 1734710483000
                        val link: String,
                        val name: String, // 剧情短片精选
                        val recSort: Int, // 0
                        val shield: Shield,
                        val videoNum: Int // 719
                    ) {
                        data class Follow(
                            val followed: Boolean, // false
                            val itemId: Int, // 2369
                            val itemType: String // author
                        )

                        data class Shield(
                            val itemId: Int, // 2369
                            val itemType: String, // author
                            val shielded: Boolean // false
                        )
                    }

                    data class Consumption(
                        val collectionCount: Int, // 0
                        val realCollectionCount: Int, // 0
                        val replyCount: Int, // 4
                        val shareCount: Int // 0
                    )

                    data class Cover(
                        val blurred: String, // http://ali-img.kaiyanapp.com/4fc8a764b10aa42b9f4589257086f70b.jpeg?imageMogr2/quality/60/format/jpg
                        val detail: String, // http://ali-img.kaiyanapp.com/774a9d77ceac9ad8d8e005ef1a246fed.jpeg?imageMogr2/quality/60/format/jpg
                        val feed: String, // http://ali-img.kaiyanapp.com/774a9d77ceac9ad8d8e005ef1a246fed.jpeg?imageMogr2/quality/60/format/jpg
                        val homepage: String?, // http://img.kaiyanapp.com/774a9d77ceac9ad8d8e005ef1a246fed.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                        val sharing: Any? // null
                    )

                    data class PlayInfo(
                        val height: Int, // 1080
                        val name: String, // 高清
                        val type: String, // high
                        val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=326500&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                        val urlList: List<Url>,
                        val width: Int // 1920
                    ) {
                        data class Url(
                            val name: String, // aliyun
                            val size: Int, // 13694656
                            val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=326500&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                        )
                    }

                    data class Provider(
                        val alias: String, // youtube
                        val icon: String, // http://ali-img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                        val name: String // YouTube
                    )

                    data class Tag(
                        val actionUrl: String, // eyepetizer://tag/1032/?title=%E7%BB%99%E4%BD%A0%E8%AE%B2%E4%B8%AA%E5%A5%BD%E6%95%85%E4%BA%8B
                        val adTrack: Any?, // null
                        val bgPicture: String, // http://img.kaiyanapp.com/d471080a9de44e8fbaa4850887273332.jpeg?imageMogr2/quality/60/format/jpg
                        val childTagIdList: Any?, // null
                        val childTagList: Any?, // null
                        val communityIndex: Int, // 0
                        val desc: String?, // 每周末更新，关注听开眼给你讲故事。
                        val haveReward: Boolean, // false
                        val headerImage: String, // http://img.kaiyanapp.com/33a2b832b7583dd9781f9fd40ad7617e.jpeg?imageMogr2/quality/60/format/jpg
                        val id: Int, // 1032
                        val ifNewest: Boolean, // false
                        val name: String, // 给你讲个好故事
                        val newestEndTime: Any?, // null
                        val tagRecType: String // IMPORTANT
                    )

                    data class WebUrl(
                        val forWeibo: String, // https://m.eyepetizer.net/u1/video-detail?video_id=326500&resource_type=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
                        val raw: String // http://www.eyepetizer.net/detail.html?vid=326500
                    )
                }
            }

            data class Cover(
                val blurred: String, // http://ali-img.kaiyanapp.com/2683b0408781c967c5851899737aeb4f.jpeg?imageMogr2/quality/60/format/jpg
                val detail: String, // http://ali-img.kaiyanapp.com/cf53022aff4b44d737a7f4e984b30b45.jpeg?imageMogr2/quality/60/format/jpg
                val feed: String, // http://ali-img.kaiyanapp.com/cf53022aff4b44d737a7f4e984b30b45.jpeg?imageMogr2/quality/60/format/jpg
                val homepage: String?, // http://img.kaiyanapp.com/cf53022aff4b44d737a7f4e984b30b45.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                val sharing: Any? // null
            )

            data class Header(
                val actionUrl: String, // eyepetizer://pgc/detail/2369/?title=%E5%89%A7%E6%83%85%E7%9F%AD%E7%89%87%E7%B2%BE%E9%80%89&userType=PGC&tabIndex=1
                val cover: Any?, // null
                val description: String, // #剧情
                val font: Any?, // null
                val icon: String, // http://ali-img.kaiyanapp.com/50fe7ed6bbb5e5e52620e8664d58d323.png?imageMogr2/quality/60/format/jpg
                val iconType: String, // round
                val id: Int, // 326500
                val label: Any?, // null
                val labelList: Any?, // null
                val rightText: Any?, // null
                val showHateVideo: Boolean, // false
                val subTitle: Any?, // null
                val subTitleFont: Any?, // null
                val textAlign: String, // left
                val time: Long, // 1734710475000
                val title: String // 剧情短片精选
            )

            data class PlayInfo(
                val height: Int, // 1080
                val name: String, // 高清
                val type: String, // high
                val url: String, // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=326702&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                val urlList: List<Url>,
                val width: Int // 1920
            ) {
                data class Url(
                    val name: String, // aliyun
                    val size: Int, // 153389518
                    val url: String // http://baobab.kaiyanapp.com/api/v1/playUrl?vid=326702&resourceType=video&editionType=high&source=aliyun&playUrlType=url_oss&udid=
                )
            }

            data class Provider(
                val alias: String, // youtube
                val icon: String, // http://ali-img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                val name: String // YouTube
            )

            data class Tag(
                val actionUrl: String, // eyepetizer://tag/1032/?title=%E7%BB%99%E4%BD%A0%E8%AE%B2%E4%B8%AA%E5%A5%BD%E6%95%85%E4%BA%8B
                val adTrack: Any?, // null
                val bgPicture: String, // http://img.kaiyanapp.com/d471080a9de44e8fbaa4850887273332.jpeg?imageMogr2/quality/60/format/jpg
                val childTagIdList: Any?, // null
                val childTagList: Any?, // null
                val communityIndex: Int, // 0
                val desc: String?, // 每周末更新，关注听开眼给你讲故事。
                val haveReward: Boolean, // false
                val headerImage: String, // http://img.kaiyanapp.com/33a2b832b7583dd9781f9fd40ad7617e.jpeg?imageMogr2/quality/60/format/jpg
                val id: Int, // 1032
                val ifNewest: Boolean, // false
                val name: String, // 给你讲个好故事
                val newestEndTime: Any?, // null
                val tagRecType: String // IMPORTANT
            )

            data class VideoPosterBean(
                val fileSizeStr: String, // 2.91MB
                val scale: Double, // 0.725
                val url: String // http://eyepetizer-videos.oss-cn-beijing.aliyuncs.com/video_poster_share/444ce202daeff95ef75ca2decee175da.mp4
            )

            data class WebUrl(
                val forWeibo: String, // https://m.eyepetizer.net/u1/video-detail?video_id=326702&resource_type=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
                val raw: String // http://www.eyepetizer.net/detail.html?vid=326702
            )
        }
    }
}