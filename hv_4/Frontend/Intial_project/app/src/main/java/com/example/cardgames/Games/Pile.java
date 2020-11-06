package com.example.cardgames.Games;

import com.example.cardgames.Games.DefaultGames.GameState;
import com.example.cardgames.R;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.cardgames.Games.HelpingClasses.CardGraphics;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.cardgames.net_utils.Const.CARD_HEIGHT;
import static com.example.cardgames.net_utils.Const.CARD_WIDTH;

/*
    Piles main use is that in every card game there is multiple areas where cards need to go. So this class will handle the information
    about where the piles are located and other rules that may be needed for the piles. For example, a card will need to move from pile
    to pile although we want it to only go to piles it should be going to. So you save the locations of the other piles that a card needs to move to.
 */

/**
 * Pile is the location that cards will be moved to and holds other information for the game
 */
public class Pile extends AppCompatActivity {

    private Point point;
    private Pile[] Receive;
    private double ownerID;
    private int cardsInPile = 0, MaxCardsInPile = 52;
    private ArrayList<Card> cards;
    private ImageView pileView;
    private boolean clicked = false, flipOnClicked;
    private CardGraphics cg;
    private GameState gs;

    /**
     * Creates the pile
     * @param cg Graphics helper class
     * @param p point the pile is located
     * @param flipOnClicked when a card is moved should the card flip
     */
    public Pile(GameState gs, CardGraphics cg, Point p, double ownerID, boolean flipOnClicked){
        this.gs = gs;
        this.flipOnClicked = flipOnClicked;
        this.cg = cg;
        this.ownerID = ownerID;
        cards = new ArrayList<>();
        point = p;
        setPile();
    }

    /**
     * Adds the card to the pile
     * @param card the card to be added
     * @return returns if it was added
     */
    public boolean addCard(Card card){
        if(canAddCard()) {
            pileView.setVisibility(View.VISIBLE);
            cards.add(0,card);
            card.setPile(this);
            cardsInPile++;
            return true;
        }
        return false;
    }

    /**
     * checks to see if a connection was already clicked
     * @return returns if a connection was clicked
     */
    public boolean connectionClicked(){
        for(int i = 0; i < Receive.length; i++){
            if(Receive[i].isClicked() && canAddCard()){
                Receive[i].moveTo(this, flipOnClicked);
                if(flipOnClicked)
                    cards.get(0).toggleIsFront();
                gs.moved(Receive[i].getOwnerID());
                Receive[i].Released();
                return true;
            }
        }
        return false;
    }
    /**
     * Moves the card to a specific location and decides if it should be flipped
     * @param pile the pile to be moved to
     * @param flipCard if the card should be flipped
     */
    public void moveTo(Pile pile, boolean flipCard) {
        if (!isEmpty() && pile.canAddCard()) {
            cards.get(0).setCardToTop(cards.get(0).getCurrentImage());
            cg.moveTo(cards.get(0).getCardImage(), cards.get(0).getImageForFlip(), pile, flipCard);
            pile.addCard(cards.remove(0));
            cardsInPile--;
        }
        else if (isEmpty())
            gs.cardsExhausted(getOwnerID());
    }
    /**
     * Moves all the cards to a specific location and decides if it should be flipped
     * @param pile the pile to be moved to
     */
    public void moveAllCards(Pile pile){
        while(!isEmpty()) {
            cards.get(0).setCardToTop(cards.get(0).getCurrentImage());
            if(cards.get(0).isFront())
                cards.get(0).toggleIsFront();
            cg.moveTo(cards.get(0).getCardImage(), cards.get(0).getImageForFlip(), pile, cards.get(0).isFront());
            pile.addCard(cards.remove(0));
            cardsInPile--;
        }
    }
    /**
     * checks if the pile is empty
     * @return returns if the pile is empty
     */
    public boolean isEmpty() {
        if (cards.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Sets the pile
     */
    public void setPile(){
        pileView = new ImageView(cg.getGameContext());
        pileView.setX(point.x);
        pileView.setY(point.y);
        pileView.setImageDrawable(cg.getGameResource().getDrawable(R.drawable.pile_place));
        pileView.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH, CARD_HEIGHT));
        pileView.setId(View.generateViewId());
        pileView.setAlpha((float) 0.6);
        cg.getGameFrame().addView(pileView);
        pileView.setVisibility(View.INVISIBLE);
    }

    /**
     * Returns the image view created for click listener
     * @return the image view for the pile
     */
    public ImageView getView(){
        return pileView;
    }
    /**
     * @return returns the top card
     */
    public Card getTopCard(){
        return cards.get(0);
    }
    /**
     * checks if you can add a card
     * @return returns if you can add a card
     */
    public boolean canAddCard(){
        return cardsInPile < MaxCardsInPile;
    }
    /**
     * Function that sets it to clicked
     */
    public void Clicked(){
        clicked = true;
    }

    /**
     * Function sets clicked to false
     */
    public void Released(){
        clicked = false;
    }
    /**
     * Sets the max cards in the pile
     * @param max the max amount of cards that will be in the pile
     */
    public void setMaxCardsInPile(int max){ MaxCardsInPile = max;}

    /**
     * gets the point of the pile
     * @return point
     */
    public Point getPoint(){
        return point;
    }

    /**
     * changes the state of the game by players id
     * @return
     */
    public double getOwnerID(){
        return ownerID;
    }
    /**
     * checks if the pile was clicked
     * @return returns if it was clicked
     */
    public boolean isClicked(){
        return clicked;
    }
    /**
     * Sets the array of the piles that can send cards to this
     * @param connections
     */
    public void setReceiveConnections(Pile[] connections){
        Receive = connections;
    }

    /**
     * Removes the pile from the frame
     */
    public void removePile(){
        cg.getGameFrame().removeView(pileView);
    }
}
