package com.example.youtubevideo

import YoutubeDataService
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubevideo.adapter.PlayListItemAdapter
import com.example.youtubevideo.model.ContentDetailsModel
import com.example.youtubevideo.model.ItemsViewModel
import com.example.youtubevideo.model.PlayListItemsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var mConstraintLayout:ConstraintLayout? = null
    private var mSearchText:EditText? = null
    private var mPlayListItemView:RecyclerView? = null

    companion object {
        var YoutubeDataAPI     = "https://www.googleapis.com/"
        val APIKey             = ""
        var contentDetailsPart = "contentDetails"
        var PlayListItemsPart  = "snippet,contentDetails,status"
        var Id                 = ""//這個可換
        var PlaylistId         = ""
        var defaultVideoURL    = "https://www.youtube.com/channel/UCQVhrypJhw1HxuRV4gX6hoQ"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mConstraintLayout = findViewById(R.id.constraintLayout)
        mSearchText = findViewById(R.id.searchText)
        mPlayListItemView = findViewById(R.id.playListItemView)
        mPlayListItemView?.layoutManager = LinearLayoutManager(this)

        //預設頻道連結
        mSearchText?.setText(defaultVideoURL);
        //按別處隱藏虛擬鍵盤
        hideSoftKeyboard()
    }
    fun Activity.hideSoftKeyboard(){
        mConstraintLayout?.setOnClickListener{
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                mSearchText?.clearFocus()
            }
        }
    }
    fun CallAPI(view:View){
        val separated: List<String> = mSearchText?.getText().toString().split("/")
        Id = separated[4]
        val retrofit = Retrofit.Builder()
            .baseUrl(YoutubeDataAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(YoutubeDataService::class.java)
        apiService.getYoutubeFeeds(APIKey, contentDetailsPart, Id)?.enqueue(object : Callback<ContentDetailsModel> {
            override fun onResponse(call: Call<ContentDetailsModel>, response: Response<ContentDetailsModel>) {
                val results = response.body()
                try{
                    PlaylistId = results?.items?.get(0)?.contentDetails?.relatedPlaylists?.uploads.toString();
                }catch (e:Exception){

                }
                //判斷是否有取到uploadsId
                if(PlaylistId.isNotEmpty()){
                    loadPlaylistItems();
                }
            }

            override fun onFailure(call: Call<ContentDetailsModel>, t: Throwable) {
                //Log.e("error", t.message.toString())
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun loadPlaylistItems() {
        val retrofit = Retrofit.Builder()
            .baseUrl(YoutubeDataAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(YoutubeDataService::class.java)
        apiService.getPlayListItems(APIKey, PlayListItemsPart, PlaylistId)?.enqueue(object : Callback<PlayListItemsModel> {
            override fun onResponse(
                call: Call<PlayListItemsModel>,
                response: Response<PlayListItemsModel>
            ) {
                val results = response.body()
                val data = ArrayList<ItemsViewModel>()
                for (p in results?.items!!){
                    data.add(ItemsViewModel(p.snippet?.thumbnails?.default?.url.toString(), p.snippet?.description.toString(), p.snippet?.title.toString(), p.contentDetails?.videoId.toString()))
                }
                val adapter = PlayListItemAdapter(data)
                mPlayListItemView?.adapter = adapter
            }

            override fun onFailure(call: Call<PlayListItemsModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}