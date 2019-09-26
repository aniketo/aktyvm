package com.aniket.videosly

data class SitesData(val name:String,val url:String,val logo:Int)

/*
data class YoutubeSearchData(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
) {
    data class Item(
        val etag: String,
        val id: Id,
        val kind: String,
        val snippet: Snippet
    ) {
        data class Id(
            val kind: String,
            val videoId: String
        )

        data class Snippet(
            val channelId: String,
            val channelTitle: String,
            val description: String,
            val liveBroadcastContent: String,
            val publishedAt: String,
            val thumbnails: Thumbnails,
            val title: String
        ) {
            data class Thumbnails(
                val default: Default,
                val high: High,
                val medium: Medium
            ) {
                data class Default(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class High(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Medium(
                    val height: Int,
                    val url: String,
                    val width: Int
                )
            }
        }
    }

    data class PageInfo(
        val resultsPerPage: Int,
        val totalResults: Int
    )
}*/

data class YoutubeSearchData(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
) {
    data class Item(
        val contentDetails: ContentDetails,
        val etag: String,
        val id: String,
        val kind: String,
        val snippet: Snippet,
        val status: Status
    ) {
        data class ContentDetails(
            val caption: String,
            val definition: String,
            val dimension: String,
            val duration: String,
            val licensedContent: Boolean,
            val projection: String
        )

        data class Snippet(
            val categoryId: String,
            val channelId: String,
            val channelTitle: String,
            val defaultAudioLanguage: String,
            val description: String,
            val liveBroadcastContent: String,
            val localized: Localized,
            val publishedAt: String,
            val tags: List<String>,
            val thumbnails: Thumbnails,
            val title: String
        ) {
            data class Localized(
                val description: String,
                val title: String
            )

            data class Thumbnails(
                val default: Default,
                val high: High,
                val medium: Medium,
                val standard: Standard
            ) {
                data class Default(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class High(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Medium(
                    val height: Int,
                    val url: String,
                    val width: Int
                )

                data class Standard(
                    val height: Int,
                    val url: String,
                    val width: Int
                )
            }
        }

        data class Status(
            val embeddable: Boolean,
            val license: String,
            val privacyStatus: String,
            val publicStatsViewable: Boolean,
            val uploadStatus: String
        )
    }

    data class PageInfo(
        val resultsPerPage: Int,
        val totalResults: Int
    )
}