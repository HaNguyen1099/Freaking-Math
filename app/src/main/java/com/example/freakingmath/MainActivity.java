package com.example.freakingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPlay;
    private MediaPlayer mediaPlayerAll;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mediaPlayerAll = MediaPlayer.create(this, R.raw.full);
        mediaPlayer = MediaPlayer.create(this, R.raw.pass);
        mediaPlayerAll.start();

        btnPlay = findViewById(R.id.play);
        btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.play){
            mediaPlayer.start();
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }
    }
}