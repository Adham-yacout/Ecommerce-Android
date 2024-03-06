package com.example.ecommerce.main.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ecommerce.R;

public class SplashScreen extends AppCompatActivity {
  Handler initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        initial=new Handler();
        initial.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this, LandingPageActivity.class);
                startActivity(intent);
            }
        },3000);


    }
}