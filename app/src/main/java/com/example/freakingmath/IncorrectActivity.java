package com.example.freakingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class IncorrectActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView goScore;
    private Button btnReplay, btnHome;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_incorrect);

        int score = getIntent().getIntExtra("score",0);
        mediaPlayer = MediaPlayer.create(this, R.raw.pass);

        goScore = findViewById(R.id.go_score);
        btnReplay = findViewById(R.id.replay);
        btnHome = findViewById(R.id.home);

        goScore.setText("Score: " + score);
        btnReplay.setOnClickListener(this);
        btnHome.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.replay){
            mediaPlayer.start();
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.home){
            mediaPlayer.start();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}