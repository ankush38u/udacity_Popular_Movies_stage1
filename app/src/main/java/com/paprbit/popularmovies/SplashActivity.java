package com.paprbit.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Runnable r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MoviesActivity.class));
                finish();
            }
        };
        handler.postDelayed(r,3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(r,3000);
    }
}
