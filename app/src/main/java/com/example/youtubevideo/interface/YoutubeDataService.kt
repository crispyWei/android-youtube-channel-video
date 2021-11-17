
import com.example.youtubevideo.model.ContentDetailsModel
import com.example.youtubevideo.model.PlayListItemsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeDataService {
    @GET("youtube/v3/channels")
    fun getYoutubeFeeds(
        @Query("key") apikey: String?,
        @Query("part") part: String?,
        @Query("id") id: String?
    ): Call<ContentDetailsModel>?

    @GET("youtube/v3/playlistItems")
    fun getPlayListItems(
        @Query("key") apikey:String?,
        @Query("part") part:String?,
        @Query("playlistId") playlistId:String?
    ):Call<PlayListItemsModel>?
}