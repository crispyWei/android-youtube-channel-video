package com.example.youtubevideo

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.MediaController
import android.widget.VideoView
import android.webkit.WebChromeClient

import android.webkit.WebSettings.PluginState




class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        var bundle = intent.getBundleExtra("BundleData")
        val videoId = bundle?.getString("videoid")

        var videoUrl = "https://www.youtube.com/embed/"+videoId;
        val frameVideo =
            "<html><body><iframe width=\"100%\" height=\"315\" src="+videoUrl+"  frameborder=\"0\" allowfullscreen></iframe></body></html>"
        Log.v("TAG", frameVideo)
        var mVideoView = findViewById<WebView>(R.id.youtubePlayView)
        mVideoView.getSettings().setJavaScriptEnabled(true)
        mVideoView.setWebChromeClient(WebChromeClient())
        mVideoView.loadData(frameVideo, "text/html", "utf-8")
    }

    fun closeYoutubePlayViewBtn(view: View){
        finish()
    }
}