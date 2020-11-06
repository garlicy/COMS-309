package com.example.cardgames.Games.DefaultGames;

import android.graphics.Point;

import com.example.cardgames.Games.HelpingClasses.Game;
import com.example.cardgames.Games.Player;

/**
 * @author Colton Hazlett
 * Temporary class until the databases and contnollers are up and running
 */
public class War implements Game {

    private final int numPiles = 6, WinningScore = 52;
    private double gameID;
    private Point[] pileLocations = new Point[numPiles];
    private int[][] receive;
    private boolean[] flipOnClick = new boolean[]{false, false, true, true, true, true};
    private int[] maxCards = new int[]{52, 52, 52, 52, 1, 1}, pileOwner= new int[]{0,1,1,0,1,0};
    private int[] comparePiles = new int[]{5, 4}, discardPiles = new int[]{2,3}, playerPiles = new int[]{0, 1};

    public War(){ ///Only have the pile have the onclick listener and move the top cards around
        setPilePoints();
        setReceiveConnection();
    }
    @Override
    public boolean isWon(Player[] players){
        for(Player p : players) {
            if (p.getScore() == WinningScore)
                return true;
        }
        return false;
    }
    @Override
    public Point[] getPiles() {
        return pileLocations;
    }
    @Override
    public int[][] getReceiveConnections() {
        return receive;
    }
    @Override
    public boolean getFlipOnClick(int index){
        return flipOnClick[index];
    }
    @Override
    public int getOwner(int i){
        return pileOwner[i];
    }

    @Override
    public int getCardScoreValue(int suit, int number){ return 1;}
    @Override
    public int getMaxCardsForPile(int i){return maxCards[i];}
    @Override
    public int getNumPlayers(){return 2;}
    @Override
    public boolean isVertical(){return true;}
    @Override
    public double getID(){
        return gameID;
    }
    @Override
    public int[] getComparePiles(){
        return comparePiles;
    }
    @Override
    public int[] getDiscardPiles(){
        return discardPiles;
    }
    @Override
    public int[] getPlayerPiles(){
        return playerPiles;
    }

    @Override
    public void setPilePoints(){
        pileLocations[0] = new Point(CENTER_HORIZONTAL,BOTTOM_SCREEN);
        pileLocations[1] = new Point(CENTER_HORIZONTAL,TOP_SCREEN);
        pileLocations[2] = new Point(RIGHT_SIDE - 20,BOTTOM_SCREEN - 200);
        pileLocations[3] = new Point(LEFT_SIDE + 20,TOP_SCREEN + 200);
        pileLocations[4] = new Point(CENTER_HORIZONTAL,CENTER_VERTICAL - 200);
        pileLocations[5] = new Point(CENTER_HORIZONTAL,CENTER_VERTICAL + 200);

    }
    @Override
    public void setReceiveConnection() {
        receive = new int[numPiles][];
        receive[3] = new int[]{4};
        receive[2] = new int[]{5};
        receive[0] = new int[]{2};
        receive[1] = new int[]{3};
        receive[4] = new int[]{1};
        receive[5] = new int[]{0};
    }

    @Override
    public void setID(double id){
        gameID = id;
    }

}
