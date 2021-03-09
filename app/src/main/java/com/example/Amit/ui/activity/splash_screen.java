package com.example.Amit.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.Amit.R;

public class splash_screen extends AppCompatActivity {
    Animation anim;
    Animation anim2;
    Animation anim3;

    ImageView imageView,i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView=findViewById(R.id.imageSignIn);
i2=findViewById(R.id.imageSignIn2);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim); // Create the animation.
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim2); // Create the animation.
        anim3= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim3); // Create the animation.
        imageView.setAnimation(anim);
        i2.setAnimation(anim3);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
        imageView.startAnimation(anim);
        i2.startAnimation(anim3);

    }

}