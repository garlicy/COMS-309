package com.example.cardgames.Games.HelpingClasses;

public class Message {

    private String name, msg;
    private int TYPE;

    public Message(String Sender, String message, int TYPE){
        name = Sender;
        msg = message;
        this.TYPE = TYPE;
    }
    public String getSender(){
        return name;
    }
    public String getMessage(){
        return msg;
    }
    public int getType(){
        return TYPE;
    }
}