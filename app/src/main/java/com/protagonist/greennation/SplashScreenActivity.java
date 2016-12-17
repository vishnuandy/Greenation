package com.protagonist.greennation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.protagonist.greennation.helper.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                SessionManager session=new SessionManager(SplashScreenActivity.this);
                if (session.isLoggedIn()) {
                    Intent i=new Intent(SplashScreenActivity.this,MyForest.class);
                    startActivity(i);

                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), SignupActivity.class));

                }

            }
        }, 5000);
    }
}
