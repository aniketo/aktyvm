package com.aniket.videosly.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import com.aniket.videosly.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import im.delight.android.webview.AdvancedWebView;

public class BrowserActivity extends AppCompatActivity {

    private AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        // mWebView.setListener(this, this);
        mWebView.loadUrl(getIntent().getStringExtra("url"));

        //mWebView.getUrl()

        FloatingActionButton f=findViewById(R.id.fab);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BrowserActivity.this,mWebView.getUrl(),Toast.LENGTH_LONG).show();
                downloadurl(mWebView.getUrl());
            }
        });
        // ...
    }



    protected void downloadurl(String url){
        new YouTubeExtractor(this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                    String downloadUrl = ytFiles.get(itag).getUrl();
                    Log.d("URL",downloadUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl));
                    intent.setDataAndType(Uri.parse(downloadUrl), "video/mp4");
                    startActivity(intent);
                }
            }
        }.extract(url, true, true);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }


    public void onPageStarted(String url, Bitmap favicon) {
    }


    public void onPageFinished(String url) {
    }


    public void onPageError(int errorCode, String description, String failingUrl) {
    }


    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }


    public void onExternalPageRequest(String url) {
    }
}
