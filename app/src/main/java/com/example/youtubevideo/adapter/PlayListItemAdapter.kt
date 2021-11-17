package com.example.youtubevideo.adapter

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubevideo.R
import com.example.youtubevideo.model.ItemsViewModel

import android.widget.Button
import coil.load
import android.content.Intent
import android.os.Bundle
import com.example.youtubevideo.VideoActivity

class PlayListItemAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<PlayListItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val titleTextView:TextView = itemView.findViewById(R.id.titleTextView)
        val watchVideoBtn:Button = itemView.findViewById(R.id.watchVideoBtn)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.imageView.load(Uri.parse(ItemsViewModel.image))
        holder.descriptionTextView.setText(HtmlCompat.fromHtml(ItemsViewModel.description, HtmlCompat.FROM_HTML_MODE_LEGACY))
        holder.titleTextView.setText(HtmlCompat.fromHtml(ItemsViewModel.title, HtmlCompat.FROM_HTML_MODE_LEGACY))

        holder.watchVideoBtn.setOnClickListener {
            val intent = Intent(holder.itemView.context, VideoActivity::class.java)
//            intent.putExtra("videoid", ItemsViewModel.videoid)
            val boundle = Bundle().apply {
                putString("videoid", ItemsViewModel.videoid)
            }
            intent.putExtra("BundleData", boundle);
            holder.itemView.context.startActivity(intent)
        }
    }
}

