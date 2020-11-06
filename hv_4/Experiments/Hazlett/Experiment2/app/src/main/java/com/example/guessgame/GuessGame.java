package com.example.guessgame;

import java.io.Serializable;

public class GuessGame implements Serializable {

    private int level, answer, lives;

    public GuessGame() {
        level = 1;
        lives = 3;
        answer = getRand();
    }
    public boolean guess(int guess){
        if(guess == answer){
            nextTurn();
            return true;
        }
        answer = getRand();
        lives--;
        return false;
    }
    public int getLives(){
        return lives;
    }
    public int getMax(){
        if(level == 5) return 10;
        else if(level == 6) return 20;
        else return (level+1);
    }
    public int getRand(){
        int rand = (int)Math.random();
        if(level == 5)
            return rand%10;
        else if(level == 6)
            return rand%20;
        else
            return rand%(level+1);
    }
    public boolean isDead(){
        if(lives == 0) return true;
        return false;
    }
    public void nextTurn() {
        level++;
        lives = 3;
        answer = getRand();
    }
    public void newGame(){
        level = 1;
        lives = 3;
        answer = getRand();
    }
}

