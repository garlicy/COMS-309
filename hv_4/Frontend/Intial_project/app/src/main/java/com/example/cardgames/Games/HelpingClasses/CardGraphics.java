package com.example.cardgames.Games.HelpingClasses;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cardgames.Games.Card;
import com.example.cardgames.Games.DefaultGames.GameState;
import com.example.cardgames.Games.Pile;
import com.example.cardgames.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Colton Hazlett
 * Class to help with the graphics of the game play
 */

public class CardGraphics {

    private FrameLayout frame;
    private Context context;
    private android.content.res.Resources res;
    private GameState gs;

    public CardGraphics(Context context, FrameLayout frame, android.content.res.Resources res){
        this.context = context;
        this.frame = frame;
        this.res = res;
    }
    public void setGameState(GameState gs){
        this.gs = gs;
    }
    public Context getGameContext(){
        return context;
    }
    public FrameLayout getGameFrame(){
        return frame;
    }
    public android.content.res.Resources getGameResource(){
        return res;
    }

    public void shuffleNDeal(Game game, ArrayList<Pile> piles, ArrayList<Card> deck, int numPlayers, int numCardsToDeal){
        Pile deal = new Pile(null, this, new Point(game.CENTER_HORIZONTAL, game.CENTER_VERTICAL), -1, false);
        for(Pile p : piles)
            p.moveAllCards(deal);

        for(int i = 0; i < numCardsToDeal; i++)
            deal.moveTo(piles.get(i%numPlayers), false);

        /*
        Will be used to put the rest of the cards in deck spot
        for(int i = 0; i < 52 - numCardsToDeal; i++){

        }*/
        deal.removePile();
    }
    public void startGameDeal(Game game, ArrayList<Pile> piles, ArrayList<Card> deck, int numPlayers, int numCardsToDeal){
        Pile deal = new Pile(null, this, new Point(game.CENTER_HORIZONTAL, game.CENTER_VERTICAL), -1, false);

        Collections.shuffle(deck);
        for(Card c : deck)
            deal.addCard(c);


        for(int i = 0; i < numCardsToDeal; i++)
            deal.moveTo(piles.get(i % numPlayers), false);


        /*
        Will be used to put the rest of the cards in deck spot
        for(int i = 0; i < 52 - numCardsToDeal; i++){

        }*/
        deal.removePile();
    }
    public void gameOver(double winnerID){
        TextView winner = new TextView(context);
        winner.setText("Winner: " + winnerID);
        winner.setTextSize(25);
        winner.setTextColor(res.getColor(R.color.colorBlack));
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rp.addRule(RelativeLayout.CENTER_IN_PARENT);
        winner.setLayoutParams(rp);
    }
    /**
     * Moves the image to specific location and will flip the card over if needed
     * @param iv the image view being moved
     * @param d the image the card is being flipped to
     * @param pile the pile the card is moving to
     * @param flip True if the image should be flipped
     */
    public void moveTo(ImageView iv, Drawable d, Pile pile, boolean flip){
        if(flip)
            flipCard(iv, d);
        move(iv, pile);
    }

    /**
     * Flips the card
     * @param iv Image being flipped
     * @param d Image that will be on the other side
     */
    private void flipCard(ImageView iv, Drawable d){
        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "rotationY", 0.0f, 90f);
        animation.setDuration(400);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
        iv.setImageDrawable(d);
        iv.setRotationX(180);
        animation = ObjectAnimator.ofFloat(iv, "rotationY", 90.0f, 180f);
        animation.setDuration(400);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();

    }

    /**
     * Moves the image to a specific location
     * @param iv the image being moved
     * @param pile the pile it is being moved to
     */
    private void move(ImageView iv, Pile pile) {
        AnimatorSet animXY = new AnimatorSet();
        ObjectAnimator animX = ObjectAnimator.ofFloat(iv, "translationX", pile.getPoint().x);
        ObjectAnimator animY = ObjectAnimator.ofFloat(iv, "translationY", pile.getPoint().y);
        animXY.playTogether(animX, animY);
        animXY.start();
    }
    /**
     * Returns the image ID of the cards picture
     * @param card the card that should be returned
     * @return
     */
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

