package com.aspprothesbarai.texttovoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private LottieAnimationView animationView1,animationView2;
    private Typeface typeface;
    private Animation animation1,animation2;
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES,WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES);
        this.getSupportActionBar().hide();
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.statusbarColor));
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.splash_screen);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        animationView1 = findViewById(R.id.animationView1);
        animationView2 = findViewById(R.id.animationView2);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/monitor.ttf");
        animation1 = AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        animation2 = AnimationUtils.loadAnimation(this,R.anim.up_from_bottom_slow);

        textView.setTypeface(typeface);
        textView.setAnimation(animation2);
        imageView.setAnimation(animation1);
        animationView1.setAnimation(animation2);
        animationView2.setAnimation(animation1);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
                goHomePage();
            }
        });
        thread.start();

    }

    public void startProgress(){
        for(progress = 20; progress<=100; progress = progress+20){
            try {
                Thread.sleep(1000);
                animationView1.setProgress(progress);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void goHomePage(){
        Intent intent = new Intent(SplashScreen.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}