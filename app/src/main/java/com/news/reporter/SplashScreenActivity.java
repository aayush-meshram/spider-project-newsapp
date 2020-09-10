package com.news.reporter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#06B2FF"))    // This is electric blue
                .withBeforeLogoText("Reporter App")
                .withAfterLogoText("For the benefit of all")
                .withLogo(R.mipmap.ic_launcher_round);

        /*Typeface OpenSans = Typeface.create("ope);
        config.getBeforeLogoTextView().setTypeface(OpenSans);*/

        config.getBeforeLogoTextView().setTextColor(getResources().getColor(R.color.white));
        config.getAfterLogoTextView().setTextColor(getResources().getColor(R.color.white));

        View view = config.create();
        setContentView(view);
    }
}