package com.example.cardgames.Games;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cardgames.R;
import com.example.cardgames.Games.HelpingClasses.CardGraphics;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.cardgames.net_utils.Const.CARD_HEIGHT;
import static com.example.cardgames.net_utils.Const.CARD_WIDTH;

/**
 * @author Colton Hazlett
 * Class that represents a playing card and holds its information
 */
public class Card extends AppCompatActivity{

    private int CardBack, CardFront, suit, number;
    private int gameValue, scoreValue;
    private boolean isVisible, isFront;
    private ImageView card;
    private Pile inPile;
    private CardGraphics cg;

    /**
     * Creates the card
     * @param cg Graphics helper class
     * @param suit the suit value of the card
     * @param number the number that correlates with the card
     * @param value value the card holds for comparison
     * @param scoreValue the value the card has for scoring
     * @param visible if the card should be visible
     */
    public Card(CardGraphics cg, int suit, int number, int value, int scoreValue, boolean visible){
        this.suit = suit;
        this.number = number;
        this.gameValue = value;
        this.scoreValue = scoreValue;
        this.cg = cg;
        isFront = false;
        setCard();
    }

    /**
     * @return returns the current image
     */
    public Drawable getCurrentImage(){
        if(isFront) return cg.getGameResource().getDrawable(CardFront);
        return cg.getGameResource().getDrawable(CardBack);
    }

    /**
     * @return returns the cards value
     */
    public int getValue(){
        return gameValue;
    }

    /**
     * @return returns what image will be flipped to
     */
    public Drawable getImageForFlip(){
        if(isFront) return cg.getGameResource().getDrawable(CardBack);
        return cg.getGameResource().getDrawable(CardFront);
    }

    /**
     * toggles the isFront boolean
     */
    public void toggleIsFront(){
        isFront = !isFront;
    }

    /**
     * @return returns if the card front is showing
     */
    public boolean isFront(){
        return isFront;
    }

    /**
     * @return Returns the cards image
     */
    public ImageView getCardImage(){
        return card;
    }

    /**
     * Sets the pile the card is in
     * @param p pile to be changed to
     */
    public void dealToPile(Pile p){
        inPile = p;
        card.setX(inPile.getPoint().x);
        card.setY(inPile.getPoint().y);
        inPile.addCard(this);
        card.setVisibility(View.VISIBLE);
    }
    /**
     * Sets the pile the card is in
     * @param p pile to be changed to
     */
    public void setPile(Pile p){
        inPile = p;
    }

    /**
     * sets the back of the card
     */
    public void setCardBack(){
        CardBack = R.drawable.blueback;
    }

    /**
     * sets the front of the card
     * @param id the image id the front should be set to
     */
    public void SetCardFront(int id){
        CardFront = id;
    }

    /**
     * Sets the card in the playing frame
     */
    public void setCard() {
        card = new ImageView(cg.getGameContext());
        card.setId(View.generateViewId());
        card.setImageDrawable(cg.getGameResource().getDrawable(R.drawable.blueback));
        setCardBack();
        card.setVisibility(View.INVISIBLE);
        card.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH, CARD_HEIGHT));
        cg.getGameFrame().addView(card);
    }

    /**
     * Function used to set the card to the top layer of the screen
     * @param image Image the card will be set to
     */
    public void setCardToTop(Drawable image) {
        cg.getGameFrame().removeView(card);
        card = new ImageView(cg.getGameContext());
        card.setId(View.generateViewId());
        card.setImageDrawable(image);
        card.setX(inPile.getPoint().x);
        card.setY(inPile.getPoint().y);
        card.setVisibility(View.VISIBLE);
        card.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH, CARD_HEIGHT));
        cg.getGameFrame().addView(card);
    }
    public String suit(){
        if(suit == 3) return "Clubs";
        if(suit == 2) return "Spades";
        if(suit == 1) return "Hearts";
        return "Diamonds";
    }
    public String number(){
        if(number == 12) return "King";
        if(number == 11) return "Queen";
        if(number == 10) return "Jack";
        if(number == 0) return "Ace";
        return "" + (number+1);
    }
}
