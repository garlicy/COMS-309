package com.example.cardgames.Games.DefaultGames;

import android.util.Log;

import com.example.cardgames.Games.HelpingClasses.Game;

import java.util.ArrayList;

/**
 * This class will be used to temporarily represent the games state
 */
public class GameState {

    private double gameID;
    private int numPlayers = 0, maxPlayers;
    private ArrayList<Double> playerIDs;
    private ArrayList<Boolean> moved, cardsExhausted;

    public GameState(Game game){
        maxPlayers = game.getNumPlayers();
        playerIDs = new ArrayList<>();
        moved = new ArrayList<>();
        cardsExhausted = new ArrayList<>();
    }
    public double createGame(){
        gameID = Math.random();
        playerIDs.add(Math.random());
        moved.add(false);
        cardsExhausted.add(false);
        Log.d("Game Created", "ID: " + gameID);
        return playerIDs.get(numPlayers++);
    }

    public double getGameID(){
        return gameID;
    }

    public double addPlayer(double id) {
        if (id == gameID && numPlayers < maxPlayers){
            playerIDs.add(Math.random());
            moved.add(false);
            cardsExhausted.add(false);
            Log.d("Player Created", "ID: " + playerIDs.get(numPlayers));
            return playerIDs.get(numPlayers++);
        }
        Log.d("Player Not created", "game ID: " + id);
        return -1;
    }
    public boolean canPlay(){
        return numPlayers == maxPlayers;
    }
    public boolean isCardsExhausted(double id){
        if(cardsExhausted.get(playerIDs.indexOf(id))){
            cardsExhausted.set(playerIDs.indexOf(id), false);
            return true;
        }
        return false;
    }
    public void cardsExhausted(double id){
        if(playerIDs.contains(id))
            cardsExhausted.set(playerIDs.indexOf(id), true);
    }
    public boolean allMoved(){
        for(Boolean b : moved){
            if(!b)
                return false;
        }
        for(int i = 0; i < moved.size(); i++)
            moved.set(i, false);
        return true;
    }
    public void moved(double playerID){
        if(playerIDs.contains(playerID))
            moved.set(playerIDs.indexOf(playerID), true);
    }
    public double getPlayerID(int i){
        return playerIDs.get(i);
    }
}
