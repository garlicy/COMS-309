package com.example.cardgames.Games.HelpingClasses;

import android.graphics.Point;

import com.example.cardgames.Games.Player;

public interface Game {

    //Final variables
    int BOTTOM_SCREEN = 1950, TOP_SCREEN = 170, RIGHT_SIDE = 1230, LEFT_SIDE = 10, CENTER_HORIZONTAL = 615, CENTER_VERTICAL = 1080;
    int DIAMONDS = 0, HEARTS = 1, SPADES = 2, CLUBS = 3;

    boolean isWon(Player[] players);
    Point[] getPiles();
    int[][] getReceiveConnections();
    boolean getFlipOnClick(int index);
    int getOwner(int i);

    int getCardScoreValue(int suit, int number);
    int getMaxCardsForPile(int i);
    int getNumPlayers();
    int[] getComparePiles();
    int[] getDiscardPiles();
    int[] getPlayerPiles();
    boolean isVertical();
    double getID();

    void setPilePoints();
    void setReceiveConnection();
    void setID(double id);

}
