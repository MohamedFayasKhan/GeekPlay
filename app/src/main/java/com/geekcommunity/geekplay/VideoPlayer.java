package com.geekcommunity.geekplay;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity {

    VideoView video;
    LinearLayout playPause;
    boolean isPlay = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        video = findViewById(R.id.video_play_video);
        playPause = findViewById(R.id.linear_play_pause);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        String path = getIntent().getStringExtra("video_path");
        startVideo(path);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay && video.isPlaying()) {
                    video.pause();
                } else {
                    video.start();
                }
            }
        });

    }

    private void startVideo(String path) {

        if (video.isPlaying()){
            video.stopPlayback();
        } else {
            video.setVideoURI(Uri.parse(path));
            video.start();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (video.isPlaying()){
            video.stopPlayback();
        }
    }
}