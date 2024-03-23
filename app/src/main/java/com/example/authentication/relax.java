package com.example.authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import java.io.IOException;
public class relax extends AppCompatActivity {

    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        Button pausebtn;
        Button playbtn;
        Button stopbtn;
        pausebtn=findViewById(R.id.pausem);
        playbtn=findViewById(R.id.playm);
        stopbtn=findViewById(R.id.stopm);

        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String aPath = "android.resource://"+getPackageName()+"/raw/relaxing";
        Uri audiouri = Uri.parse(aPath);
        try {
            mp.setDataSource(this, audiouri);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pausebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.pause();
            }
        });

        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mp.stop() will release media player ref
                mp.pause();
                mp.seekTo(0); //will move to alloted position
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Stop the media player when the back button is pressed
        if (mp != null && mp.isPlaying()) {
            mp.stop();
        }

        // Call super.onBackPressed() to allow the default back button behavior
        super.onBackPressed();
    }
    @Override
    protected void onDestroy() {
        // Release the MediaPlayer resources when the activity is destroyed
        if (mp != null) {
            mp.release();
            mp = null;
        }

        super.onDestroy();
    }
}
