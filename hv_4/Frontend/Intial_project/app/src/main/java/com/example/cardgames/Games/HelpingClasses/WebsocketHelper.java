package com.example.cardgames.Games.HelpingClasses;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.java_websocket.client.WebSocketClient;

import java.util.ArrayList;

public class WebsocketHelper {

    private WebSocketClient client;
    private ArrayList<String> messages;
    private ArrayAdapter<String> adapter;
    private ListView message_View;

    public WebsocketHelper(WebSocketClient client, Context context, ListView message_View){
        this.client = client;
        messages = new ArrayList<>();
        this.message_View = message_View;
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, messages);
        if(this.message_View == null)
            Log.d("Message View", "NULL");
        this.message_View.setAdapter(adapter);
    }
    public void sendMessage(String message){
        try {
            client.send(message);
            //sendChat(message);
        } catch (Exception e) {
            Log.d("ExceptionSendMessage:", e.getMessage().toString());
        }
    }
    public void sendChat(String message){
        messages.add(message);
        adapter.notifyDataSetChanged();
    }
}
