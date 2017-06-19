package com.example.alex.update.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.alex.update.R;

/**
 * Created by alex on 6/19/17.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread() {
            public void run() {
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        //TODO Auto-genetated method stub
        super.onPause();
        finish();
    }
}

