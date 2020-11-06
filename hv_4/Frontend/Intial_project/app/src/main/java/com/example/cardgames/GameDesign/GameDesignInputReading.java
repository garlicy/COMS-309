package com.example.cardgames.GameDesign;

import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * @author Benjamin Goodall
 * This class holds useful methods and constants used for game design.
 */
public class GameDesignInputReading {

    /**
     * ID to store and retrieve the Game Data
     */
    public static final String gameDataID = "gameData";
    /**
     * ID to store and retrieve the instructions
     */
    public static final String instructionsID = "instructions";
    /**
     * ID to store and retrieve the game name
     */
    public static final String gameNameID = "gameName";
    /**
     * ID to store and retrieve the number of players
     */
    public static final String numPlayersID = "numPlayers";
    /**
     * ID to store and retrieve the number of decks
     */
    public static final String numDecksID = "numDecks";
    /**
     * ID to store and retrieve the total number of rounds
     */
    public static final String totalRoundsID = "totalRounds";
    /**
     * ID to store and retrieve the points goal
     */
    public static final String pointsGoalID = "pointsGoal";
    /**
     * ID to store and retrieve the choice of ending condition
     */
    public static final String endChoiceID = "endChoice";
    /**
     * ID to store and retrieve the list of play area names
     */
    public static final String playAreaNamesID = "playAreaNames";
    /**
     * ID to store and retrieve the list of play area visibility options
     */
    public static final String playAreaVisibilityID = "playAreaVisibility";
    /**
     * ID to store and retrieve the bundle containing each phases information
     */
    public static final String phasesID = "phases";
    /**
     * ID to store and retrieve the deck to exhaust to end the game
     */
    public static final String toExhaustID = "toExhaust";

    public static final String xCoordinateID = "xCoordinate";

    public static final String yCoordinateID = "yCoordinate";

    public static final String cardPointListID = "cardPointList";

    public static final String playAreaDestinationsID = "playAreaDestinations";
    /**
     * Reads the Radio Group to find the child that has text that matches the given text.
     * @param text the text the desired child has
     * @param radios the radio group you are searching
     * @return the first RadioButton whose text matches the given text, or null if none match.
     */
    public static RadioButton getRadioToCheck(String text, RadioGroup radios) {
        for (int i = 0; i < radios.getChildCount(); i++) {
            RadioButton child = (RadioButton) radios.getChildAt(i);
            if (child.getText().equals(text)) {
                return child;
            }
        }
        return null;
    }

    public static ArrayList<String> cardNames()
    {
        String[] rank = new String[13];
        rank[0] = "Ace";
        rank[1] = "Two";
        rank[2] = "Three";
        rank[3] = "Four";
        rank[4] = "Five";
        rank[5] = "Six";
        rank[6] = "Seven";
        rank[7] = "Eight";
        rank[8] = "Nine";
        rank[9] = "Ten";
        rank[10] = "Jack";
        rank[11] = "Queen";
        rank[12] = "King";
        String[] suit = new String[4];
        suit[0] = "Diamonds";
        suit[1] = "Clubs";
        suit[2] = "Hearts";
        suit[3] = "Spades";
        ArrayList<String> deck = new ArrayList<>();
        for(int i = 0; i < rank.length; i++)
        {
            for(int j = 0; j < suit.length; j++)
            {
                deck.add(rank[i] + " of " + suit[j]);
            }
        }
        return deck;
    }
}
