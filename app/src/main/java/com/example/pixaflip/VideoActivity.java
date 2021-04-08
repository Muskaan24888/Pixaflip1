package com.example.pixaflip;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pixaflip.sql.MyDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VideoActivity extends AppCompatActivity {

    private String getDateTime()
    {
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date();
        return sd.format(d);
    }
    private MyDbHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        final VideoView video = (VideoView) findViewById(R.id.videoView);


       // VideoView videoView = findViewById(R.id.playVideo1);
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);

        video.start();



        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


            public void onCompletion(MediaPlayer mp) {
                Intent in = new Intent (getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
        });

    }
}
