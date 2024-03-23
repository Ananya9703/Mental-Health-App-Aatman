package com.example.authentication;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


public class Breath extends AppCompatActivity {
    private static final String TAG = Breath.class.getSimpleName();
    private TextView statusText;
    private View outerCircleView, innerCircleView;


    private Animation animationInhaleText, animationExhaleText, animationInhaleInnerCircle, animationExhaleInnerCircle;
    private Handler handler = new Handler();


    private int holdDuration = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath);
        statusText = findViewById(R.id.txt_status);
        statusText.setText("INHALE");


        outerCircleView = findViewById(R.id.view_circle_outer);
        innerCircleView = findViewById(R.id.view_circle_inner);


        //setupBackgroundColor();


        prepareAnimations();
        statusText.startAnimation(animationInhaleText);
        innerCircleView.startAnimation(animationInhaleInnerCircle);
    }


    private void setupBackgroundColor() {
        //setOuterCircleBackground(R.color.teal_700);
    }


    private void setOuterCircleBackground(int backgroundResId) {
        outerCircleView.setBackgroundResource(backgroundResId);
    }


    private void setInhaleDuration(int duration) {
        animationInhaleText.setDuration(duration);
        animationInhaleInnerCircle.setDuration(duration);
    }


    private void setExhaleDuration(int duration) {
        animationExhaleText.setDuration(duration);
        animationExhaleInnerCircle.setDuration(duration);
    }


    private void prepareAnimations() {
        int inhaleDuration = 4000;
        int exhaleDuration = 4000;
        holdDuration = 2000;


        // Inhale - make large
        animationInhaleText = AnimationUtils.loadAnimation(this, R.anim.anim_text_inhale);
        animationInhaleText.setFillAfter(true);
        animationInhaleText.setAnimationListener(inhaleAnimationListener);


        animationInhaleInnerCircle = AnimationUtils.loadAnimation(this, R.anim.anim_inner_circle_inhale);
        animationInhaleInnerCircle.setFillAfter(true);
        animationInhaleInnerCircle.setAnimationListener(inhaleAnimationListener);


        setInhaleDuration(inhaleDuration);


        // Exhale - make small
        animationExhaleText = AnimationUtils.loadAnimation(this, R.anim.anim_text_exhale);
        animationExhaleText.setFillAfter(true);
        animationExhaleText.setAnimationListener(exhaleAnimationListener);


        animationExhaleInnerCircle = AnimationUtils.loadAnimation(this, R.anim.anim_inner_circle_exhale);
        animationExhaleInnerCircle.setFillAfter(true);
        animationExhaleInnerCircle.setAnimationListener(exhaleAnimationListener);


        setExhaleDuration(exhaleDuration);


    }


    private Animation.AnimationListener inhaleAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {


        }


        @Override
        public void onAnimationEnd(Animation animation) {
            Log.d(TAG, "inhale animation end");
            statusText.setText("HOLD");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    statusText.setText("EXHALE");
                    statusText.setTextSize(14);
                    statusText.startAnimation(animationExhaleText);
                    innerCircleView.startAnimation(animationExhaleInnerCircle);
                }
            }, holdDuration);
        }


        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };


    private Animation.AnimationListener exhaleAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
        }


        @Override
        public void onAnimationEnd(Animation animation) {
            Log.d(TAG, "exhale animation end");
            statusText.setText("HOLD");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    statusText.setText("INHALE");
                    statusText.setTextSize(14);
                    statusText.startAnimation(animationInhaleText);
                    innerCircleView.startAnimation(animationInhaleInnerCircle);
                }
            }, holdDuration);
        }


        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };
}


