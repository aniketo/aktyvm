package com.aniket.videosly.webservices

import com.aniket.videosly.YOUTUBE_API
import com.aniket.videosly.YOUTUBE_DATA_URL
import com.aniket.videosly.YoutubeSearchData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET


object YoutubeAPI {
    private var apiInterface: ApiInterface? = null

    fun getService(): ApiInterface? {
        if (apiInterface == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(YOUTUBE_DATA_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiInterface = retrofit.create(ApiInterface::class.java)
        }
        return apiInterface
    }

    interface ApiInterface {


        @GET("?part=snippet%2CcontentDetails%2Cstatus&chart=mostPopular&maxResults=30&regionCode=in&key=${YOUTUBE_API}")
        fun fetchVideos(): Call<YoutubeSearchData>
    }


}