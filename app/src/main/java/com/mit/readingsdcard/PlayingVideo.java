package com.mit.readingsdcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.io.File;

public class PlayingVideo extends AppCompatActivity {
    VideoView video;
    MediaController media;
    TextView txtVideoName;
    String path;

    void initViews(){
        video=(VideoView)findViewById(R.id.videoView);
        media=new MediaController(this);
        txtVideoName=(TextView)findViewById(R.id.textViewVideoName);

        Intent data=getIntent();
        path=data.getStringExtra("keyPath");
        File file=new File(path);
        txtVideoName.setText(file.getName());
    }

    void videoPlay(){
        video.setVideoPath(path);
        media.setAnchorView(video);
        video.setMediaController(media);
        video.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hides Notification Bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.playing_video);
        initViews();
        videoPlay();
    }
}
