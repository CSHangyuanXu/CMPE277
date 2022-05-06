package com.xhy.shoppingmall.app;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xhy.shoppingmall.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //1 seconds in to mainpage
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },1000);
    }
}