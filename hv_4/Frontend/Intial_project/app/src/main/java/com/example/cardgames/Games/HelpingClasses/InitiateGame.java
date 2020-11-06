package com.example.cardgames.Games.HelpingClasses;

import android.graphics.Point;
import android.util.Log;

import com.example.cardgames.Games.Card;
import com.example.cardgames.Games.DefaultGames.Golf;
import com.example.cardgames.Games.Pile;
import com.example.cardgames.Games.DefaultGames.GameState;
import com.example.cardgames.Games.DefaultGames.War;

import java.util.ArrayList;
import java.util.Collections;

public class InitiateGame {

    private CardGraphics cg;
    private GameState gs;

    /**
     *Helping class to initiate the game
     * @param cg the graphics helper class
     */
    public InitiateGame(CardGraphics cg){
        this.cg = cg;
        this.gs = gs;
    }

    /**
     * Creates and initializes the information used for the game
     * @param game the game that is being played
     * @param piles the piles used in the game
     * @param deck the deck being played
     */
    public void setGame(GameState gs, Game game, ArrayList<Pile> piles, ArrayList<Card> deck){
        setPiles(piles, gs, game);
        setDeck(deck, piles, game);
    }
    /**
     * Sets the piles that will be used in the game
     * @param piles the array of piles
     * @param game the game being played
     */
    public void setPiles(ArrayList<Pile> piles, GameState gs, Game game){
        Point[] points = game.getPiles();
        for(int i = 0; i < points.length; i++){
            piles.add(new Pile(gs, cg, points[i], gs.getPlayerID(game.getOwner(i)), game.getFlipOnClick(i)));
            piles.get(i).setMaxCardsInPile(game.getMaxCardsForPile(i));
        }
        int[][] ids = game.getReceiveConnections();
        for(int i = 0; i < ids.length; i++){
            Pile[] r = new Pile[ids[i].length];
            for(int j = 0; j < ids[i].length; j++){
                r[j] = piles.get(ids[i][j]);
            }
            piles.get(i).setReceiveConnections(r);
        }
    }
    /**
     * Sets the deck to be used in the game with images, piles, and other information
     * @param deck the deck array used
     * @param piles the piles the cards will be added to
     * @param game the game being played
     */
    public void setDeck(ArrayList<Card> deck, ArrayList<Pile> piles, Game game){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Card c = new Card(cg, i, j, j, game.getCardScoreValue(i, j),  true);
                c.SetCardFront(cg.assignImage((i*100)+j));
                deck.add(c);
            }
        }
        Collections.shuffle(deck);
        for(int i = 0; i < 52; i++)
            deck.get(i).dealToPile(piles.get((8)));

    }
    /**
     * @param name the name of the game
     * @return returns the game
     */
    public Game getGame(String name){
        if(name.equals("War"))
            return new War();
        if(name.equals("Golf"))
            return new Golf();
        return null;
    }
}
