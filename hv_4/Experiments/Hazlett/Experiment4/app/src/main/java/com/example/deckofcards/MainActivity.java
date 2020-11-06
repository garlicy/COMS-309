package com.example.deckofcards;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.deckofcards.R.*;

public class MainActivity extends AppCompatActivity{

    private int homeX = 0, homeY = 0, firstCard = -870;
    ImageView Deck, Card1, Card2, Card3, Card4, Card5;
    Button redeal;
    ArrayList<Integer> cards;
    int clicks = 0, x = firstCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Deck = findViewById(id.Deck);
        Card1 = findViewById(id.Card1);
        Card2 = findViewById(id.Card2);
        Card3 = findViewById(id.Card3);
        Card4 = findViewById(id.Card4);
        Card5 = findViewById(id.Card5);
        redeal = findViewById(id.RedealButton);
        redeal.setVisibility(View.INVISIBLE);

        cards = new ArrayList<>();
        for(int i = 0; i < 4;i++){ //Diamond,Hearts,Spades,Clubs
            for(int j = 0; j < 13; j++){
                cards.add((i*100)+j);
            }
        }
        Collections.shuffle(cards);
        Deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x+=290;
                ImageView v;
                if(clicks == 0)
                    v = Card1;
                else if(clicks == 1)
                    v = Card2;
                else if(clicks == 2)
                    v = Card3;
                else if(clicks == 3)
                    v = Card4;
                else if(clicks == 4)
                    v = Card5;
                else{
                    redeal.setVisibility(View.VISIBLE);
                    return;
                }
                ObjectAnimator animX = ObjectAnimator.ofFloat(v, "translationX", x);
                ObjectAnimator animY = ObjectAnimator.ofFloat(v, "translationY", 1940);
                AnimatorSet animSetXY = new AnimatorSet();
                animSetXY.playTogether(animX, animY);
                animSetXY.start();
                assignImage(cards.get(clicks), v);
                v.setVisibility(View.VISIBLE);
                clicks++;
            }
        });
        redeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView v;
                for(int i = 0; i < 5; i++) {
                    if (i == 0)
                        v = Card1;
                    else if (i == 1)
                        v = Card2;
                    else if (i == 2)
                        v = Card3;
                    else if (i == 3)
                        v = Card4;
                    else
                        v = Card5;
                    ObjectAnimator animX = ObjectAnimator.ofFloat(v, "translationX", homeX);
                    ObjectAnimator animY = ObjectAnimator.ofFloat(v, "translationY", homeY);
                    AnimatorSet animSetXY = new AnimatorSet();
                    animSetXY.playTogether(animX, animY);
                    animSetXY.start();
                    assignImage(-1, v);
                }
                clicks = 0;
                x = firstCard;
                Collections.shuffle(cards);
                redeal.setVisibility(View.INVISIBLE);
            }
        });
    }


    public void assignImage(int card, ImageView image){
        switch (card){
            case(0):
                image.setImageDrawable(this.getDrawable(drawable.ad));
                break;
            case 1:
                image.setImageDrawable(this.getDrawable(drawable.d2));
                break;
            case(2):
                image.setImageDrawable(this.getDrawable(drawable.d3));
                break;
            case(3):
                image.setImageDrawable(this.getDrawable(drawable.d4));
                break;
            case(4):
                image.setImageDrawable(this.getDrawable(drawable.d5));
                break;
            case(5):
                image.setImageDrawable(this.getDrawable(drawable.d6));
                break;
            case(6):
                image.setImageDrawable(this.getDrawable(drawable.d7));
                break;
            case(7):
                image.setImageDrawable(this.getDrawable(drawable.d8));
                break;
            case(8):
                image.setImageDrawable(this.getDrawable(drawable.d9));
                break;
            case(9):
                image.setImageDrawable(this.getDrawable(drawable.d10));
                break;
            case(10):
                image.setImageDrawable(this.getDrawable(drawable.jd));
                break;
            case(11):
                image.setImageDrawable(this.getDrawable(drawable.qd));
                break;
            case(12):
                image.setImageDrawable(this.getDrawable(drawable.kd));
                break;
            case(100):
                image.setImageDrawable(this.getDrawable(drawable.ah));
                break;
            case(101):
                image.setImageDrawable(this.getDrawable(drawable.h2));
                break;
            case(102):
                image.setImageDrawable(this.getDrawable(drawable.h3));
                break;
            case(103):
                image.setImageDrawable(this.getDrawable(drawable.h4));
                break;
            case(104):
                image.setImageDrawable(this.getDrawable(drawable.h5));
                break;
            case(105):
                image.setImageDrawable(this.getDrawable(drawable.h6));
                break;
            case(106):
                image.setImageDrawable(this.getDrawable(drawable.h7));
                break;
            case(107):
                image.setImageDrawable(this.getDrawable(drawable.h8));
                break;
            case(108):
                image.setImageDrawable(this.getDrawable(drawable.h9));
                break;
            case(109):
                image.setImageDrawable(this.getDrawable(drawable.h10));
                break;
            case(110):
                image.setImageDrawable(this.getDrawable(drawable.jh));
                break;
            case(111):
                image.setImageDrawable(this.getDrawable(drawable.qh));
                break;
            case(112):
                image.setImageDrawable(this.getDrawable(drawable.kh));
                break;
            case(200):
                image.setImageDrawable(this.getDrawable(drawable.as));
                break;
            case(201):
                image.setImageDrawable(this.getDrawable(drawable.s2));
                break;
            case(202):
                image.setImageDrawable(this.getDrawable(drawable.s3));
                break;
            case(203):
                image.setImageDrawable(this.getDrawable(drawable.s4));
                break;
            case(204):
                image.setImageDrawable(this.getDrawable(drawable.s5));
                break;
            case(205):
                image.setImageDrawable(this.getDrawable(drawable.s6));
                break;
            case(206):
                image.setImageDrawable(this.getDrawable(drawable.s7));
                break;
            case(207):
                image.setImageDrawable(this.getDrawable(drawable.s8));
                break;
            case(208):
                image.setImageDrawable(this.getDrawable(drawable.s9));
                break;
            case(209):
                image.setImageDrawable(this.getDrawable(drawable.s10));
                break;
            case(210):
                image.setImageDrawable(this.getDrawable(drawable.js));
                break;
            case(211):
                image.setImageDrawable(this.getDrawable(drawable.qs));
                break;
            case(212):
                image.setImageDrawable(this.getDrawable(drawable.ks));
                break;
            case(300):
                image.setImageDrawable(this.getDrawable(drawable.ac));
                break;
            case(301):
                image.setImageDrawable(this.getDrawable(drawable.c2));
                break;
            case(302):
                image.setImageDrawable(this.getDrawable(drawable.c3));
                break;
            case(303):
                image.setImageDrawable(this.getDrawable(drawable.c4));
                break;
            case(304):
                image.setImageDrawable(this.getDrawable(drawable.c5));
                break;
            case(305):
                image.setImageDrawable(this.getDrawable(drawable.c6));
                break;
            case(306):
                image.setImageDrawable(this.getDrawable(drawable.c7));
                break;
            case(307):
                image.setImageDrawable(this.getDrawable(drawable.c8));
                break;
            case(308):
                image.setImageDrawable(this.getDrawable(drawable.c9));
                break;
            case(309):
                image.setImageDrawable(this.getDrawable(drawable.c10));
                break;
            case(310):
                image.setImageDrawable(this.getDrawable(drawable.jc));
                break;
            case(311):
                image.setImageDrawable(this.getDrawable(drawable.qc));
                break;
            case(312):
                image.setImageDrawable(this.getDrawable(drawable.kc));
                break;
            case(-1):
                image.setImageDrawable(this.getDrawable(drawable.blueback));
        }
    }

}
