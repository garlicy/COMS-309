package com.example.cardgames.Games.HelpingClasses;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;

import com.example.cardgames.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import androidx.fragment.app.DialogFragment;

public class ChatScreen extends BottomSheetDialogFragment {

    private EditText text;
    private ImageButton send;
    private ListView messages;
    private MessageAdapter messageAdapter;
    private WebSocketClient client;


    /**
     * The view that is created on the pop up screen
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.x = ViewGroup.LayoutParams.MATCH_PARENT;
        p.y = 500;
        getDialog().getWindow().setAttributes(p);
        View view = inflater.inflate(R.layout.activity_chat_screen, container, false);
        initializeView(view);
        return view;
    }

    public void initializeView(View view){
        text = view.findViewById(R.id.textbox);
        send = view.findViewById(R.id.SendChat_BTN);
        messages = view.findViewById(R.id.messages_view);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("");
            }
        });
    }

}

/*
{
    "MessageType" :  CHAT / JOIN / LEAVE
    "Content" : the message
    "Sender" : the username
 */
