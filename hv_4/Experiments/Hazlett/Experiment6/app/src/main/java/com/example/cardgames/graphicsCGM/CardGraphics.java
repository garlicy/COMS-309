package com.example.cardgames.graphicsCGM;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.example.cardgames.R;

public class CardGraphics {


    public CardGraphics(){

    }
    public void flipCard(ImageView iv, Drawable d){
        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "rotationY", 0.0f, 90f);
        animation.setDuration(1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
        iv.setImageDrawable(d);
        iv.setRotationX(180);
        animation = ObjectAnimator.ofFloat(iv, "rotationY", 90.0f, 180f);
        animation.setDuration(1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

    }
    public void goTo(ImageView iv, int x, int y) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(iv, "translationX", x);
        ObjectAnimator animY = ObjectAnimator.ofFloat(iv, "translationY", y);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.start();

    }

    public int assignImage(int card){
        switch (card){
            case(0):
                return R.drawable.ad;
            case(1):
                return R.drawable.d2;
            case(2):
                return R.drawable.d3;
            case(3):
                return R.drawable.d4;
            case(4):
                return R.drawable.d5;
            case(5):
                return R.drawable.d6;
            case(6):
                return R.drawable.d7;
            case(7):
                return R.drawable.d8;
            case(8):
                return R.drawable.d9;
            case(9):
                return R.drawable.d10;
            case(10):
                return R.drawable.jd;
            case(11):
                return R.drawable.qd;
            case(12):
                return R.drawable.kd;
            case(100):
                return R.drawable.ah;
            case(101):
                return R.drawable.h2;
            case(102):
                return R.drawable.h3;
            case(103):
                return R.drawable.h4;
            case(104):
                return R.drawable.h5;
            case(105):
                return R.drawable.h6;
            case(106):
                return R.drawable.h7;
            case(107):
                return R.drawable.h8;
            case(108):
                return R.drawable.h9;
            case(109):
                return R.drawable.h10;
            case(110):
                return R.drawable.jh;
            case(111):
                return R.drawable.qh;
            case(112):
                return R.drawable.kh;
            case(200):
                return R.drawable.as;
            case(201):
                return R.drawable.s2;
            case(202):
                return R.drawable.s3;
            case(203):
                return R.drawable.s4;
            case(204):
                return R.drawable.s5;
            case(205):
                return R.drawable.s6;
            case(206):
                return R.drawable.s7;
            case(207):
                return R.drawable.s8;
            case(208):
                return R.drawable.s9;
            case(209):
                return R.drawable.s10;
            case(210):
                return R.drawable.js;
            case(211):
                return R.drawable.qs;
            case(212):
                return R.drawable.ks;
            case(300):
                return R.drawable.ac;
            case(301):
                return R.drawable.c2;
            case(302):
                return R.drawable.c3;
            case(303):
                return R.drawable.c4;
            case(304):
                return R.drawable.c5;
            case(305):
                return R.drawable.c6;
            case(306):
                return R.drawable.c7;
            case(307):
                return R.drawable.c8;
            case(308):
                return R.drawable.c9;
            case(309):
                return R.drawable.c10;
            case(310):
                return R.drawable.jc;
            case(311):
                return R.drawable.qc;
            case(312):
                return R.drawable.kc;
            case(-1):
                return R.drawable.blueback;
        }
        return -1;
    }
}

