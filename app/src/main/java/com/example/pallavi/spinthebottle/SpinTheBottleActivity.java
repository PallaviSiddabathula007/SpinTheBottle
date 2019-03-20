package com.example.pallavi.spinthebottle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class SpinTheBottleActivity extends AppCompatActivity {

    private ImageView mBottle;
    private Random mRandomNumber;
    private int mLastDir;
    private boolean mIsBottleSpinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_the_bottle);

        loadView();
    }

    private void loadView() {
        mRandomNumber = new Random();
        mBottle = (ImageView) findViewById(R.id.bottle);
        mBottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinTheBottle();
            }
        });
    }

    private void spinTheBottle() {
        if (!mIsBottleSpinning) {
            int newDir = mRandomNumber.nextInt(1500);
            float pivotX = mBottle.getWidth() / 2;
            float pivotY = mBottle.getHeight() / 2;

            Animation bottleSpin = new RotateAnimation(mLastDir, newDir, pivotX, pivotY);
            bottleSpin.setDuration(2500);
            bottleSpin.setFillAfter(true);
            bottleSpin.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mIsBottleSpinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mIsBottleSpinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            mLastDir = newDir;
            mBottle.startAnimation(bottleSpin);
        }
    }
}
