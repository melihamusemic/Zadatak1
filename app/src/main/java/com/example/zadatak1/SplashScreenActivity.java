package com.example.zadatak1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToLoginActivity();
    }

    private void goToLoginActivity() {
        int timeout = 5000; // make the activity visible for 5 seconds
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent homepage = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(homepage);
            }
        }, timeout);
    }
}