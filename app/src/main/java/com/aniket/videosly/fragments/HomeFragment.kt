package com.aniket.videosly.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aniket.videosly.*

import com.aniket.videosly.adapters.SitesRecycler
import com.aniket.videosly.adapters.VideoRecycler
import com.aniket.videosly.webservices.YoutubeAPI
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    var c=1
    val videosList = ArrayList<YoutubeSearchData.Item>()
    lateinit var video_adapter: VideoRecycler

    val sitesList = ArrayList<SitesData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //startActivity(Intent(context,TestActivity::class.java))
        
        sites_recycler.layoutManager = GridLayoutManager(activity, 5) as RecyclerView.LayoutManager?
        sites_recycler.addItemDecoration(SpagesItemDecoration(5, 0, false));
        sites_recycler.adapter = SitesRecycler(context!!, sitesList)

        rec_videos.layoutManager = GridLayoutManager(activity, 2)
        rec_videos.addItemDecoration(SpagesItemDecoration(2, 24, false))

        video_adapter = VideoRecycler(context!!, videosList)
        rec_videos.adapter = video_adapter

        if (c==1){
            addLinkCards()
            fetchVideo()

        }



    }

    override fun onResume() {
        super.onResume()
        c++
    }

    fun addLinkCards(){
        sitesList.add(SitesData("Youtube", "http://www.youtube.com", R.drawable.nav_youtube))
        sitesList.add(SitesData("Facebook", "http://www.facebook.com", R.drawable.nav_facebook))
        sitesList.add(SitesData("Instagram", "http://www.instagram.com", R.drawable.nav_instagram))
        sitesList.add(SitesData("WhatsApp", "http://www.whatsapp.com", R.drawable.nav_whatsapp))



    }

    fun fetchVideo() {
        val call = YoutubeAPI.getService()!!.fetchVideos()
        call.enqueue(object : Callback<YoutubeSearchData> {
            override fun onFailure(call: Call<YoutubeSearchData>, t: Throwable) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<YoutubeSearchData>, response: Response<YoutubeSearchData>) {
                //Toast.makeText(activity, response.body().toString(), Toast.LENGTH_SHORT).show()
                //Log.d("API", response.body().toString())
                if (response.isSuccessful && response.body()!=null){
                    videosList.addAll(response.body()!!.items)
                    video_adapter.notifyDataSetChanged()
                }
                Log.d("TTTT",response.raw().toString())
                Toast.makeText(activity, response.raw().toString(), Toast.LENGTH_SHORT).show()

            }

        })
    }
}
