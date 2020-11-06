package com.example.cardgames.Games;

import android.util.Log;

import com.example.cardgames.Games.HelpingClasses.Game;

import java.util.ArrayList;

public class Rules {

    private Game game;
    private Pile[] comparePiles, discardPiles, playerPiles;


    public Rules(Game game, ArrayList<Pile> piles){
        this.game = game;
        playerPiles = new Pile[game.getPlayerPiles().length];
        comparePiles = new Pile[game.getComparePiles().length];
        discardPiles = new Pile[game.getDiscardPiles().length];
        int[] compare = game.getComparePiles(), discard = game.getDiscardPiles(), player = game.getPlayerPiles();
        for(int i = 0; i < comparePiles.length; i++){
            playerPiles[i] = piles.get(player[i]);
            comparePiles[i] = piles.get(compare[i]);
            discardPiles[i] = piles.get(discard[i]);
        }
    }
    /**
     * Compare will be the function to decide who won the hand
     * @return returns how it compares
     */
    public int endHand(){
        if(comparePiles[0].getTopCard().getValue() > comparePiles[1].getTopCard().getValue()){
            Log.d("ME", comparePiles[0].getTopCard().number() + " "  + comparePiles[0].getTopCard().suit() + " beats " + comparePiles[1].getTopCard().number() + " " + comparePiles[1].getTopCard().suit());
            comparePiles[0].moveTo(discardPiles[0], true);
            comparePiles[1].moveTo(discardPiles[0], true);
            return 1;
        }
        else if(comparePiles[0].getTopCard().getValue() < comparePiles[1].getTopCard().getValue()) {
            Log.d("OPPONENT", comparePiles[1].getTopCard().number() + " "  + comparePiles[1].getTopCard().suit() + " beats " + comparePiles[0].getTopCard().number() + " "  + comparePiles[0].getTopCard().suit());
            comparePiles[0].moveTo(discardPiles[1], true);
            comparePiles[1].moveTo(discardPiles[1], true);
            return -1;
        }
        else{
            Log.d("BOTH", comparePiles[0].getTopCard().number() + " "  + comparePiles[0].getTopCard().suit() + " ties " + comparePiles[1].getTopCard().number() + " "  + comparePiles[1].getTopCard().suit());
            comparePiles[0].moveTo(discardPiles[0], true);
            comparePiles[1].moveTo(discardPiles[1], true);
            return 0;
        }
    }
    public boolean checkCardsExhausted(){
        for(int i = 0; i < playerPiles.length; i++){
            if(playerPiles[i].isEmpty()){
                if(discardPiles[i].isEmpty())
                    return true;
                discardPiles[i].moveAllCards(playerPiles[i]);
            }
        }
        return false;
    }
    public double getWinner() {
        for (int i = 0; i < playerPiles.length; i++) {
            if(!playerPiles[i].isEmpty() || !discardPiles[i].isEmpty())
                return playerPiles[i].getOwnerID();
        }
        return 0;
    }
}
