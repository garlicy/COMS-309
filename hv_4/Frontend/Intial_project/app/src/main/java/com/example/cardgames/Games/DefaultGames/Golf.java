package com.example.cardgames.Games.DefaultGames;

import android.graphics.Point;

import com.example.cardgames.Games.HelpingClasses.Game;
import com.example.cardgames.Games.Player;

public class Golf implements Game {

    private final int numPiles = 11, WinningScore = 52;
    private double gameID;
    private Point[] pileLocations = new Point[numPiles];
    private int[][] receive;
    private boolean[] flipOnClick = new boolean[]{true, true, true, true, true, true, true, true, true, true, true};
    private int[] maxCards = new int[]{1,1,1,1,1,1,1,1,52,52, 1}, pileOwner= new int[]{0,0,0,0,1,1,1,1,0,0, 0};
    private int[] comparePiles = new int[]{5, 4}, discardPiles = new int[]{2,3}, playerPiles = new int[]{0, 1};

    public Golf(){ ///Only have the pile have the onclick listener and move the top cards around
        setPilePoints();
        setReceiveConnection();
    }
    @Override
    public boolean isWon(Player[] players) {
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
    public boolean getFlipOnClick(int index) {
        return flipOnClick[index];
    }

    @Override
    public int getOwner(int i) {
        return pileOwner[i];
    }

    @Override
    public int getCardScoreValue(int suit, int number) {
        if(number == 5) return -5;
        if(number == 13) return 0;
        if(number == 12 || number == 11) return 10;
        return number;
    }

    @Override
    public int getMaxCardsForPile(int i) {
        return maxCards[i];
    }

    @Override
    public int getNumPlayers() {
        return 2;
    }

    @Override
    public int[] getComparePiles() {
        return new int[0];
    }

    @Override
    public int[] getDiscardPiles() {
        return new int[0];
    }

    @Override
    public int[] getPlayerPiles() {
        return new int[0];
    }

    @Override
    public boolean isVertical() {
        return true;
    }

    @Override
    public double getID() {
        return gameID;
    }

    @Override
    public void setPilePoints() {
        pileLocations[0] = new Point(CENTER_HORIZONTAL - 120, BOTTOM_SCREEN);
        pileLocations[1] = new Point(CENTER_HORIZONTAL + 120, BOTTOM_SCREEN);
        pileLocations[2] = new Point(CENTER_HORIZONTAL - 120, BOTTOM_SCREEN - 320);
        pileLocations[3] = new Point(CENTER_HORIZONTAL + 120, BOTTOM_SCREEN - 320);

        pileLocations[4] = new Point(CENTER_HORIZONTAL - 120, TOP_SCREEN);
        pileLocations[5] = new Point(CENTER_HORIZONTAL + 120, TOP_SCREEN);
        pileLocations[6] = new Point(CENTER_HORIZONTAL - 120, TOP_SCREEN + 320);
        pileLocations[7] = new Point(CENTER_HORIZONTAL + 120, TOP_SCREEN + 320);

        pileLocations[8] = new Point(CENTER_HORIZONTAL - 120, CENTER_VERTICAL);
        pileLocations[9] = new Point(CENTER_HORIZONTAL + 120, CENTER_VERTICAL);
        pileLocations[10] = new Point(CENTER_HORIZONTAL - 360, CENTER_VERTICAL);
    }

    @Override
    public void setReceiveConnection() {
        receive = new int[numPiles][];
        receive[0] = new int[]{0, 8, 9};
        receive[1] = new int[]{1, 8, 9};
        receive[2] = new int[]{2, 8, 9};
        receive[3] = new int[]{3, 8, 9};
        receive[4] = new int[]{4, 8, 9};
        receive[5] = new int[]{5, 8, 9};
        receive[6] = new int[]{6, 8, 9};
        receive[7] = new int[]{7, 8, 9};
        receive[8] = new int[]{};
        receive[9] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 10};
        receive[10] = new int[]{9};
    }

    @Override
    public void setID(double id) {
        gameID = id;
    }
}
