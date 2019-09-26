package com.aniket.videosly.adapters


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aniket.videosly.R
import com.aniket.videosly.activities.YoutubePlayerViewActivity
import com.aniket.videosly.YoutubeSearchData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*


class VideoRecycler(val context: Context, val list: List<YoutubeSearchData.Item>) :
    RecyclerView.Adapter<VideoRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Recycler", "Test")
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.video_row, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position].snippet

        holder.tvtitle.text = item.title


        Picasso.with(context)
            .load(item.thumbnails.medium.url)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, YoutubePlayerViewActivity::class.java)
            intent.putExtra("video_id", list[position].id)
            context.startActivity(intent)
            Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
        }
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvtitle = v.title
        val image = v.image_poster
    }

}