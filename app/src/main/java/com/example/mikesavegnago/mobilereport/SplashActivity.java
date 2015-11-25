package com.example.mikesavegnago.mobilereport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Mike Savegnago on 24/11/2015.
 */
public class SplashActivity extends Activity{
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            /* * Exibindo splash com um timer. */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar // E inicia a activity principal
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i); // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
