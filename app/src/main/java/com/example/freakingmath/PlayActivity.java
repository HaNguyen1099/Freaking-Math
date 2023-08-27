package com.example.freakingmath;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    // time to play each level
    final int time = 2000;
    // view on screen
    private TextView tvMath, tvScore;
    Button btnCorrect, btnIncorrect;
    // timer
    private Timer timer;
    private TimerTask timerTask;
    private CountDownTimer countDownTimer;
    // player score
    private int score = 0;
    // level model
    private LevelModel model;

    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private ProgressBar timePB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.win);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.lose);

        timePB = findViewById(R.id.timePB);

        tvMath = findViewById(R.id.math);
        tvScore = findViewById(R.id.score);
        btnCorrect = findViewById(R.id.correct);
        btnIncorrect = findViewById(R.id.inCorrect);

        btnCorrect.setOnClickListener(this);
        btnIncorrect.setOnClickListener(this);

        model = GenerateLevel.generateLevel(1);
        displayNewLevel(model);
        createTimerTask();
        startCountdownTimer();
    }
    private void displayNewLevel(LevelModel model){
        tvMath.setText(model.strOperator);
    }
    private void createTimerTask(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameOver(score);
                    }
                });
            }
        };
        timer.schedule(timerTask, time);
    }
    private void startCountdownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Hủy CountDownTimer nếu nó đang chạy
        }
        countDownTimer = new CountDownTimer(time, 200) { // Đếm ngược mỗi 1 giây
            @Override
            public void onTick(long millisUntilFinished) {
                int currentTime = (int) millisUntilFinished;
                timePB.setProgress(currentTime);
            }
            @Override
            public void onFinish() {
                startCountdownTimer();
            }
        };
        countDownTimer.start();
    }
    private void showGameOver(final int score){
        mediaPlayer2.start();
        cancelTimer();
        Intent intent = new Intent(PlayActivity.this, IncorrectActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.correct){
            if(model.correctWrong) {
                mediaPlayer1.start();
                score += 1;
                nextLevel(score);
                startCountdownTimer();
            }
            else {
                showGameOver(score);
            }
        }
        if(id == R.id.inCorrect){
            if(!model.correctWrong) {
                mediaPlayer1.start();
                score += 1;
                nextLevel(score);
                startCountdownTimer();
            }
            else {
                showGameOver(score);
            }
        }
    }
    private void nextLevel(int score){
        tvScore.setText(String.valueOf(score));
        cancelTimer();
        createTimerTask();
        model = GenerateLevel.generateLevel(score);
        displayNewLevel(model);
    }
    private void cancelTimer(){
        timerTask.cancel();
        timer.cancel();
    }
}