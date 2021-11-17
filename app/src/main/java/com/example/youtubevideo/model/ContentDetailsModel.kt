package com.example.youtubevideo.model
import com.google.gson.annotations.SerializedName

class ContentDetailsModel {
    @SerializedName("kind")
    var kind: String = ""

    @SerializedName("etag")
    var etag: String = ""

    @SerializedName("pageInfo")
    var pageInfo: PageInfo? = null
    class PageInfo {
        @SerializedName("totalResults")
        var totalResults: Int? = null
        @SerializedName("resultsPerPage")
        var resultsPerPage: Int? = null
    }

    @SerializedName("items")
    var items: List<ContentDetailsModel>? = null

    @SerializedName("contentDetails")
    var contentDetails: ContentDetails? = null
    class ContentDetails{
        @SerializedName("relatedPlaylists")
        var relatedPlaylists:RelatedPlaylists? = null
        class RelatedPlaylists{
            @SerializedName("uploads")
            var uploads:String? = null

            @SerializedName("likes")
            var likes:String? = null
        }
    }
}