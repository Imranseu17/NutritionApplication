package com.example.nutritionapplication.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nutritionapplication.R;
import com.example.nutritionapplication.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding splashActivityViewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashActivityViewBinding = DataBindingUtil.
                setContentView(SplashActivity.this, R.layout.activity_splash);
        getSupportActionBar().setTitle("Start Page");
        splashActivityViewBinding.smpletext.setText("Travel Like You Never Lost");
        splashActivityViewBinding.dummytext.
                setText("Simply dummy text that make the design beautiful");
        splashActivityViewBinding.btnget.setText("Get Started");
        splashActivityViewBinding.bgone.setImageResource(R.drawable.image);
        splashActivityViewBinding.bgone.animate().scaleX(2).scaleY(2).setDuration(5000).start();
        splashActivityViewBinding.btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        });
    }
}
