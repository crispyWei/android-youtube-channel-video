package com.example.youtubevideo.model

import com.google.gson.annotations.SerializedName

class PlayListItemsModel {
    @SerializedName("kind")
    var kind: String = ""

    @SerializedName("etag")
    var etag: String = ""

    @SerializedName("nextPageToken")
    var nextPageToken:String = ""

    @SerializedName("items")
    var items: List<PlayListItemsModel>? = null

    @SerializedName("snippet")
    var snippet: Snippet? = null
    class Snippet{
        @SerializedName("publishedAt")
        var publishedAt:String? = null

        @SerializedName("channelId")
        var channelId:String? = null

        @SerializedName("title")
        var title:String? = null

        @SerializedName("description")
        var description:String? = null

        @SerializedName("thumbnails")
        var thumbnails:Thumbnails? = null

        class Thumbnails{
            @SerializedName("default")
            var default:Default? = null
            class Default{
                @SerializedName("url")
                var url:String? = null
                @SerializedName("width")
                var width:Int? = null
                @SerializedName("height")
                var height:Int? = null
            }
            @SerializedName("medium")
            var medium:Medium? = null
            class Medium{
                @SerializedName("url")
                var url:String? = null
                @SerializedName("width")
                var width:Int? = null
                @SerializedName("height")
                var height:Int? = null
            }
            @SerializedName("high")
            var high:High? = null
            class High{
                @SerializedName("url")
                var url:String? = null
                @SerializedName("width")
                var width:Int? = null
                @SerializedName("height")
                var height:Int? = null
            }
            @SerializedName("standard")
            var standard:Standard? = null
            class Standard{
                @SerializedName("url")
                var url:String? = null
                @SerializedName("width")
                var width:Int? = null
                @SerializedName("height")
                var height:Int? = null
            }
            @SerializedName("maxres")
            var maxres:Maxres? = null
            class Maxres {
                @SerializedName("url")
                var url:String? = null
                @SerializedName("width")
                var width:Int? = null
                @SerializedName("height")
                var height:Int? = null
            }
        }

        @SerializedName("channelTitle")
        var channelTitle:String? = null

        @SerializedName("playlistId")
        var playlistId:String? = null

        @SerializedName("position")
        var position:String? = null

        var resourceId:ResourceId? = null
        class ResourceId {
            @SerializedName("kind")
            var kind: String? = null

            @SerializedName("videoId")
            var videoId: String? = null
        }
        @SerializedName("videoOwnerChannelTitle")
        var videoOwnerChannelTitle:String? = null

        @SerializedName("videoOwnerChannelId")
        var videoOwnerChannelId:String? = null
    }
    @SerializedName("contentDetails")
    var contentDetails: ContentDetails? = null
    class ContentDetails{
        @SerializedName("videoId")
        var videoId:String? = null
        @SerializedName("videoPublishedAt")
        var videoPublishedAt:String? = null
    }

    @SerializedName("status")
    var status: Status? = null
    class Status{
        @SerializedName("privacyStatus")
        var privacyStatus:String? = null
    }
}