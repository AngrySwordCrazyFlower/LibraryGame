package com.example.crazyflower.librarygame;

/**
 * Created by CrazyFlower on 2017/8/11.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity{

    private final int SPLASH_DISPLAY_LENGHT = 2000; //延迟三秒

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent mainIntent = new Intent(Splash.this,SignIn.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}