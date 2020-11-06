package com.example.gamewar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import static android.animation.ObjectAnimator.*;

import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.gamewar.R.*;

public class MainActivity extends AppCompatActivity{

    private CardGraphics cg = new CardGraphics();
    private War war = new War();
    ImageView Deck, Pile, OpponentPile, Play, OppPlay;
    ArrayList<Integer> cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        Deck = findViewById(id.Deck);
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
                cg.flipCard(Deck, getImage(cards.get(getCard())),0);
                ImageView image = null;
                if (war.needShuffle()) {
                    for (int i = 0; i < 52; i++) {
                        if(i%2==0)
                            cg.goTo(image,0); //Goes to my cards
                        else
                            cg.goTo(image,4); //Goes to opponents cards
                    }
                }
            }
        });
    }
    public int getCard(){
        return 1;
    }

    public void assignImage(ImageView image, int card){
        image.setImageDrawable(this.getDrawable(cg.assignImage(card)));
    }
    public Drawable getImage(int card){
        return this.getDrawable(cg.assignImage(card));
    }

}
