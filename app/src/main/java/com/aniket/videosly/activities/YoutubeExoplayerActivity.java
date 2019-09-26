package com.aniket.videosly.activities;

import android.os.Bundle;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;
import com.aniket.videosly.ExoPlayerManager;
import com.aniket.videosly.R;
import com.google.android.exoplayer2.ui.PlayerView;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class YoutubeExoplayerActivity extends AppCompatActivity {
    // Replace video id with your video Id
    private String YOUTUBE_VIDEO_ID ="6VjF638VObA";
    private String BASE_URL = "https://www.youtube.com";
    private String mYoutubeLink = BASE_URL + "/watch?v=" + YOUTUBE_VIDEO_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extractYoutubeUrl();
    }

    private void extractYoutubeUrl() {
 /*       @SuppressLint("StaticFieldLeak") YouTubeExtractor mExtractor = new YouTubeExtractor(this) {
            @Override
            protected void onExtractionComplete(SparseArray<YtFile> sparseArray, VideoMeta videoMeta) {
                if (sparseArray != null) {

                    Log.d("CHECK",sparseArray.toString());
                    playVideo(sparseArray.get(0).getUrl());
                }
            }
        };
        mExtractor.extract(mYoutubeLink, true, true);*/

        new YouTubeExtractor(this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                    String downloadUrl = ytFiles.get(itag).getUrl();
                }
            }
        }.extract(mYoutubeLink, true, true);
    }

    private void playVideo(String downloadUrl) {
        PlayerView mPlayerView = findViewById(R.id.mPlayerView);
        mPlayerView.setPlayer(ExoPlayerManager.getSharedInstance(YoutubeExoplayerActivity.this).getPlayerView().getPlayer());
        ExoPlayerManager.getSharedInstance(YoutubeExoplayerActivity.this).playStream(downloadUrl);
    }


}

