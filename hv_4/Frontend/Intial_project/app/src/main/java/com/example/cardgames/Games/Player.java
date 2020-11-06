package com.example.cardgames.Games;

import android.util.Log;

import java.util.ArrayList;

/**
 * @author Colton Hazlett
 * Basic information the players use
 */
public class Player {

    private boolean isCPU;
    private int score = 0, pileCounter = 0, compare = -2;
    private int[] pilesIndex;
    private boolean OpponentMoved = false, moved = false;
    private ArrayList<Pile> piles;
    private Player opponent;

    /**
     * Creates a player
     * @param isCPU if it is a CPU
     */
    public Player(int playerID){
        piles = new ArrayList<>();
        this.isCPU = isCPU;
    }

    /**
     * compares the two top cards
     * @param p the player it is playing against
     * @return returns how it compares
     */
    private int compare(Player p){
        if(p.piles.get(p.pilesIndex[1]).getTopCard().getValue() > piles.get(pilesIndex[1]).getTopCard().getValue())
           return -1;
        else if(p.piles.get(p.pilesIndex[1]).getTopCard().getValue() < piles.get(pilesIndex[1]).getTopCard().getValue())
            return 1;
        else
            return 0;
    }

    /**
     * returns the score of the of the player
     * @return
     */
    public int getScore(){
        return score;
    }
}
